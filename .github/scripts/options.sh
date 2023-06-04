#!/bin/bash

package="com.openmeteo.api"

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

# convert snake_case to CamelCase, enum values
CamelCase() {
  cat | sed -r 's/(^|_)(\w)/\U\2/g' # https://stackoverflow.com/a/34420162/9373031
}

# convert snake_case to camelCase, property names
camelCase() {
  cat | sed -r 's/(_)(\w)/\U\2/g'
}

# join the list values with serial names and enum names and prepare enum
_options() {
  listed="$(list)"
  properties="$(echo "$listed" | camelCase | prefix 'const val ')"
  values="$(echo "$listed" | prefix '"' | suffix '"')"
  {
  cat <<END
@Serializable
object ${name^} : Options.${name^} {
END
  paste -d $'=' <(echo "$properties") <(echo "$values") \
  | prefix "    "
  echo "}"
  }
}

options() {
  _options < "tmp/$endpoint.html" #> "${endpoint,,}/$endpoint${name^}.kt"
}

declare -A docs=(
  #[AirQuality]="https://open-meteo.com/en/docs/air-quality-api"
  #[Ecmwf]="https://open-meteo.com/en/docs/ecmwf-api"
  #[Dwd]="https://open-meteo.com/en/docs/dwd-api"
  #[MeteoFrance]="https://open-meteo.com/en/docs/meteofrance-api"
  [Forecast]="https://open-meteo.com/en/docs"
  #[Historical]="https://open-meteo.com/en/docs/historical-weather-api"
  #[Marine]="https://open-meteo.com/en/docs/marine-weather-api"
  #[Gfs]="https://open-meteo.com/en/docs/gfs-api"
  #[Jma]="https://open-meteo.com/en/docs/jma-api"
  #[MetNo]="https://open-meteo.com/en/docs/metno-api"
  #[Gem]="https://open-meteo.com/en/docs/gem-api"
  #[Flood]="https://open-meteo.com/en/docs/flood-api"
)

# should run in project root
cd "lib/src/main/kotlin/$(echo "$package" | tr '.' '/')" || exit 1

# fetch html only once
mkdir tmp
for endpoint in "${!docs[@]}"; do
  curl -s "${docs[$endpoint]}" > "tmp/$endpoint.html"
done

name="hourly"
for endpoint in Forecast; do
  options
done

echo # new line

name="daily"
for endpoint in Forecast; do
  options
done

# delete html
rm -rf tmp
