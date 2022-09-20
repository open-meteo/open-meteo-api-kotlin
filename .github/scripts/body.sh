declare -A types=(
  [fix]=":bug: Fixes"
  [feat]=":rocket: New features"
  # [refactor]=":building_construction: Refactoring"
  [build]=":hammer: Build"
  [test]=":test_tube: Tests"
  [chore]=":wrench: Chores"
  [docs]=":orange_book: Docs"
  # [perf]=":zap: Performance"
  # [style]=":art: Style"
)

# [ -d body ] && rm -rf body # useful when debugging
mkdir body

while read -r commit; do
    
  IFS=' ' read -r -a c <<< "$commit"

  url="${c[0]}"
  
  [ "${c[1]: -1}" != ':' ] \
  && echo "Warning: in commit '${url:2:7}': missing <type>!" 1>&2 \
  && continue

  type="${c[1]:: -1}" # trim ':'
  [ "${type: -1}" == "!" ] && touch body/!
  type="${type/%\!/}" # trim '!' at the end

  # scope shall be at the end of the string
  [ "${type/%(*)/}" != "$type" ] \
  && scope="${type#*\(}" \
  || scope=")"

  scope="${scope:: -1}"

  type="${type%%\(*}"

  [ ! -v "types[$type]" ] \
  && echo "Warning: in commit '${url:2:7}': '$type' is not a mapped type!" 1>&2 \
  && continue

  title="${c[@]:2}"
  echo " - ${c[0]} - ${title^} ${scope:+_($scope)_}" >> "body/$type"

done < commits

# the order we want is the following:
for type in feat fix docs test chore build
do [ -a "body/$type" ] && cat<<EOF
## ${types[$type]}

$(cat "body/$type")

EOF
done

# return 0 even if last type file was missing
exit 0
