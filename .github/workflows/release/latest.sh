#!/bin/bash

# get latest release tag with pre-releases
# see: https://stackoverflow.com/a/52680984/9373031
echo "latest=$(git -c 'versionsort.suffix=-' tag --sort=-v:refname \
| grep -E -o -m 1 "$regex" | head -1)"
