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
_options() {
  listed="$(list)"
  serialNames="$(echo "$listed" | SerialName)"
  options="$(echo "$listed" | CamelCase | suffix ',')"
  {
  cat <<END
package $package.$endpoint

import $package.common.query.Query${name^}
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ${name^} : Query${name^}.Options {
END
  paste -d $'\n' <(echo "$serialNames") <(echo "$options") \
  | prefix "    "
  echo "}"
  }
}

options() {
  _options < "tmp/$endpoint.html" > "$endpoint/${name^}.kt"
}

declare -A docs=(
  [airquality]="https://open-meteo.com/en/docs/air-quality-api"
  [ecmwf]="https://open-meteo.com/en/docs/ecmwf-api"
  [forecast]="https://open-meteo.com/en/docs"
  [historical]="https://open-meteo.com/en/docs/historical-weather-api"
  [marine]="https://open-meteo.com/en/docs/marine-weather-api"
  [gfs]="https://open-meteo.com/en/docs/gfs-api"
)

# should run in project root
cd "lib/src/main/kotlin/$(echo "$package" | tr '.' '/')" || exit 1

# fetch html only once
mkdir tmp
for endpoint in "${!docs[@]}"; do
  curl -s "${docs[$endpoint]}" > "tmp/$endpoint.html"
done

name="hourly"
for endpoint in airquality ecmwf forecast historical marine gfs; do
  options
done

name="daily"
for endpoint in forecast historical marine gfs; do
  options
done

# delete html
rm -rf tmp
