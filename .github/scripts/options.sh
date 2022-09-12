#!/bin/bash

package="com.openmeteo.apix"

# list all checkboxes matching a name (hourly/daily) in a doc page
list() {
  if [ -v 'name' ]; then
    cat \
    | grep -B1 "name=\"$name\"" \
    | grep -o 'value=".*"' \
    | cut -d'"' -f2
  else
    echo "Missing name!" >&2
  fi
}

prefix() {
  cat | sed "s/^/$1/"
}

suffix() {
  cat | sed "s/$/$1/"
}

# convert snake_case to CamelCase
CamelCase() {
  cat | sed -r 's/(^|_)(\w)/\U\2/g' # https://stackoverflow.com/a/34420162/9373031
}

# convert snake_case to camelCase
camelCase() {
  cat | sed -r 's/(_)(\w)/\U\2/g'
}

# wrap the serial name
SerialName() {
  cat | prefix '@SerialName("' | suffix '")'
}

# join the list values with serial names and enum names and prepare enum
options() {
  options="$(echo "$listed" | CamelCase | suffix ',')"
  cat <<END
package $package.$endpoint

import $package.common.query.Query${name^}
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ${name^}Options : Query${name^}.Options {
END
  paste -d $'\n' <(echo "$serialNames") <(echo "$options") \
  | prefix "    "
  echo "}"
}

units() {
  units="$(echo "$listed" | camelCase | prefix "val " | suffix ': Unit? = null,')"
  cat <<END
package $package.$endpoint

import $package.common.response.Response${name^}
import $package.common.time.TimeFormat
import $package.common.units.Unit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ${name^}Units(
    override val time: TimeFormat,
END
  paste -d $'\n' <(echo "$serialNames") <(echo "$units") \
  | prefix "    "
  echo ") : Response${name^}.Units"
}

values() {
  values="$(echo "$listed" | camelCase | prefix "val " | suffix ': Array<Float?>? = null,')"
  cat <<END
package $package.$endpoint

import $package.common.response.Response${name^}
import $package.common.time.Time
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ${name^}Values(
    override val time: Array<Time>,
END
  paste -d $'\n' <(echo "$serialNames") <(echo "$values") \
  | prefix "    "
  echo ") : Response${name^}.Values"
}

group() {
  listed="$(list)"
  serialNames="$(echo "$listed" | SerialName)"
  options < "tmp/$endpoint.html" > "$endpoint/${name^}Options.kt"
  units < "tmp/$endpoint.html" > "$endpoint/${name^}Units.kt"
  values < "tmp/$endpoint.html" > "$endpoint/${name^}Values.kt"
  if [ "$name" == "daily" ]; then
    sed -i -r 's/(sunrise|sunset): Unit/\1: TimeFormat/' "$endpoint/DailyUnits.kt"
    sed -i -r 's/(sunrise|sunset): Array<Float\?>/\1: Array<Time\?>/' "$endpoint/DailyValues.kt"
  fi
}

declare -A docs=(
  [airquality]="https://open-meteo.com/en/docs/air-quality-api"
  [ecmwf]="https://open-meteo.com/en/docs/ecmwf-api"
  [forecast]="https://open-meteo.com/en/docs"
  [historical]="https://open-meteo.com/en/docs/historical-weather-api"
  [marine]="https://open-meteo.com/en/docs/marine-weather-api"
)

# should run in project root
cd "lib/src/main/kotlin/$(echo "$package" | tr '.' '/')" || exit 1

# fetch html only once
mkdir tmp
for endpoint in "${!docs[@]}"; do
  curl -s "${docs[$endpoint]}" > "tmp/$endpoint.html"
done

name="hourly"
for endpoint in airquality ecmwf forecast historical marine; do
  group < "tmp/$endpoint.html"
done

name="daily"
for endpoint in forecast historical marine; do
  group < "tmp/$endpoint.html"
done

# delete html
rm -rf tmp
