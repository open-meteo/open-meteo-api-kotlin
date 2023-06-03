#!/bin/bash

# Usage from project root dir:
# .github/scripts/version.sh bump 0.1.2-alpha.3

bump() {
  [ -v "version" ] || exit 1

  sed -i "s/version = '.*'/version = '$version'/" "lib/build.gradle"
  sed -i "s/implementation 'com.open-meteo:open-meteo-api-kotlin:.*'/implementation 'com.open-meteo:open-meteo-api-kotlin:$version'/" "INSTALLATION.md"
  sed -i "s/<version>.*<\/version>/<version>$version<\/version>/" "INSTALLATION.md"

  git add "lib/build.gradle" "INSTALLATION.md"
  git commit -m "Version $version" -- "lib/build.gradle" "INSTALLATION.md"
  git tag "$version"
  # git push origin "$version" --tags
}

if [ "$1" == "bump" ]; then
  version="$2" bump
fi
