#!/bin/bash

# You should call this script with the following environment variables set:
#
# regex="((0|[1-9][0-9]*)\.){2}(0|[1-9][0-9]*)(-(alpha|beta|rc)(\.(0|[1-9][0-9]*))?)?"
# level= 0   |   1   |   2   |   3   |   4
# old=<major>.<minor>.<patch>[-<name>[.<id>]]
# new=<major>.<minor>.<patch>[-<name>[.<id>]]

[[ ! "$old" =~ ^$regex$ ]] && exit 1

IFS='.-' read -r -a old <<< "$old"

valid=0 # 0 means "not valid", other values are for "reason" debugging
declare -a ver
if [[ "$new" =~ ^$regex$ ]]; then

  IFS='.-' read -r -a ver <<< "$new"
  for i in $(seq 0 $level); do
    if [ $i == 3 ]; then
      # same pre-release
      if [ "${ver[3]}" == "${old[3]}" ]; then
        continue
      # left pre-release stage
      elif [ ${#ver[3]} == 0 ]; then
        valid=1
        break
      # entered pre-release stage (ko because we know the first part is equal -> check for if!)
      elif [ ${#ver[3]} == 0 ]; then
        break
      # a "grater" pre-release: "a(alpha)" > "b(eta)" > "r(c)"?
      elif [[ "${ver[3]}" > "${old[3]}" ]]; then
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

# if ver should should be generated
if [ $valid == 0 ]; then
    ver=(${old[@]})
    if [[ $level == 4 ]]; then
        ver[$level]=$(( ${ver[$level]}+1 ))
    elif [[ $level == 3 ]]; then
        # if we increase pre-release name we also reset the id
        ver=(${ver[@]::4})
        if [ "${ver[3]}" == "alpha" ]; then
            ver[3]="beta"
        elif [ "${ver[3]}" == "beta" ]; then
            ver[3]="rc"
        elif [ "${ver[3]}" == "rc" ]; then
            ver=(${ver[@]::3}) # leave pre-release stage
            ver[2]=$(( ${ver[2]}+1 ))
        fi
    else
        ver=(${ver[@]::3}) # no pre-release
        ver[$level]=$(( ${ver[$level]}+1 ))
        # if we increase major/minor we want to also reset minor/patch
        [[ $level == 0 ]] && ver[1]=0
        [[ $level == 0 ]] || [[ $level == 1 ]] && ver[2]=0
    fi
fi

echo "version=${ver[0]}.${ver[1]}.${ver[2]}${ver[3]:+-${ver[3]}}${ver[4]:+.${ver[4]}}"
