#!/bin/bash

# You should call this script with the following environment variables set:
#
# regex="((0|[1-9][0-9]*)\.){2}(0|[1-9][0-9]*)(-(alpha|beta|rc)(\.(0|[1-9][0-9]*))?)?"
# level= 0   |   1   |   2   |   3   |   4
# old=<major>.<minor>.<patch>[-<name>[.<id>]]
# new=<major>.<minor>.<patch>[-<name>[.<id>]]

declare -A pre=(
  [alpha]=1
  [beta]=2
  [rc]=3
  [0]=""
  [1]="alpha"
  [2]="beta"
  [3]="rc"
)

[[ ! "$old" =~ ^$regex$ ]] && exit 1

IFS='.-' read -r -a old <<< "$old"
[ -v 'old[3]' ] && old[3]="${pre[${old[3]}]}"

valid=0 # 0 means "not valid", other values are for "reason" debugging
declare -a ver
if [[ "$new" =~ ^$regex$ ]]; then

  IFS='.-' read -r -a ver <<< "$new"
  [ -v 'ver[3]' ] && ver[3]="${pre[${ver[3]}]}"
  for i in $(seq 0 $level); do
    if [[ $i == 3 ]]; then
      # same pre-release name
      if [[ ${ver[3]} == ${old[3]} ]]; then
        continue
      # left pre-release stage
      elif [[ ${ver[3]} == 0 ]]; then
        valid=1
        break
      # entered pre-release stage (ko; we know the part before is equal!)
      # eg: 1.0.0 --> 1.0.0-alpha is bad
      elif [[ ${old[3]} == 0 ]]; then
        break
      # a "grater" pre-release?
      elif [[ ${ver[3]} -gt ${old[3]} ]]; then
        valid=2
        break
      fi
    elif [[ ${ver[$i]} -gt ${old[$i]} ]]; then
      valid=2
      break
    elif [[ ${ver[$i]} -lt ${old[$i]} ]]; then
      break
    fi
  done

fi

step() {
  ver[$1]=$(( ${ver[$1]}+1 ))
}

# if ver should should be generated
if [ $valid == 0 ]; then
  ver=(${old[@]})
  step "$level"
  if [[ $level == 3 ]]; then
    # if no name was found increase patch as well
    if [[ ! -v "pre[${ver[3]}]" ]]; then
      ver[3]=1
      step 2
    # if we just got in alpha increase patch as well
    elif [[ ${ver[3]} == 1 ]]; then
      step 2
    fi
  # if we just got in alpha increase patch as well
  elif [[ $level == 4 ]] && [[ ! -v 'ver[3]' ]]; then
      step 2
  fi
  for i in $(seq $(( level+1 )) 4); do
    # reset all parts after the one increased
    ver[$i]=0
  done
fi

# fix: bash is ***... it thinks ver[3] is missing?
ver=(${ver[@]})
ver[3]="${pre[${ver[3]}]}"

echo "version=${ver[0]}.${ver[1]}.${ver[2]}${ver[3]:+-${ver[3]}.${ver[4]:-0}}"
