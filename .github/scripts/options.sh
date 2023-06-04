#!/bin/bash

# run from root dir with `models="" endpoint=Forecast .github/scripts/options.sh`

declare -A docs=(
  [Forecast]="https://open-meteo.com/en/docs"
  [Historical]="https://open-meteo.com/en/docs/historical-weather-api"
  [Marine]="https://open-meteo.com/en/docs/marine-weather-api"
  [ClimateChange]="https://open-meteo.com/en/docs/climate-api"
  [AirQuality]="https://open-meteo.com/en/docs/air-quality-api"
  [Flood]="https://open-meteo.com/en/docs/flood-api"
)

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

# should run in project root
cd "lib/src/main/kotlin/$(echo "$package" | tr '.' '/')" || exit 1

# fetch html only once
mkdir tmp
curl -s "${docs[$endpoint]}" > "tmp/$endpoint.html"

if [ -v models ]; then
  name="models"
  options

  echo # new line
fi

name="hourly"
options

echo # new line

name="daily"
options

# delete html
rm -rf tmp
