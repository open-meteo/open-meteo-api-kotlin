#!/bin/bash

group() {
  list=$(grep "^$2;" commits | cut -d';' -f2-)
  [[ -n "$list" ]] && cat <<EOF
## $1

$list

EOF
}

# types titles
declare -A types=(
  [feat]=":rocket: New features"
  [fix]=":bug: Fixes"
  [docs]=":orange_book: Docs"
  [test]=":test_tube: Tests"
  [build]=":hammer: Build"
  [chore]=":broom: Chore"
  [style]=":nail_care: Style"
  [refactor]=":face_in_clouds: Refactoring"
  [revert]=":leftwards_arrow_with_hook: Reverted commits"
)

level=4

# prepare commits list
IFS=$'\n'
for commit in $(git log "$latest"..@ --pretty=format:"[\`%h\`](../../commit/%H) %s"); do

  url="${commit%% *}"
  title="${commit#* }"

  # split type
  type="${title%%: *}"
  # ": " delimiter wasn't found?
  if [ ${#type} == ${#title} ]; then
    echo >&2 "Warning: type ': ' delimiter missing"
    continue
  fi
  
  # split title
  title="${title#*: }"
  # empty title
  if [ ${#title} == 0 ]; then
    echo >&2 "Warning: empty title"
    continue
  fi
  
  # if commit includes breaking changes
  [ "${type: -1}" == "!" ] && level=0
  type=${type/%\!/} # trim final !

  # type is not valid?
  if [ ! -v 'types[$type]' ]; then
    echo >&2 "Warning: '$type' is not a valid type"
    # it's just a warning... no need to exit
    continue
  fi

  # adjust level based on type "importance"
  [ "$type" == "feat" ] && [ $level -gt 1 ] && level=1
  [ "$type" == "fix" ] && [ $level -gt 2 ] && level=2
  
  # `${title^}` makes first char capital case: "pretty"
  echo "$type; - $url - ${title^}"

done > commits

# is commits file empty?
if [ ! -s commits ]; then
  echo >&2 "Error: no new commits since last tag (empty body)!"
  exit 1
fi

# output all groups to BODY.md
for type in "${!types[@]}"; do
  group "${types[$type]}" "$type"
done > BODY.md

# github env
echo "level=$level"

# clean-up
rm commits
