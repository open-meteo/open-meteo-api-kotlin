#!/bin/bash

regex='((0|[1-9][0-9]*)\.){2}(0|[1-9][0-9]*)(-(alpha|beta|rc)(\.(0|[1-9][0-9]*))?)?'

t() {
  new="$(regex=$regex old=$1 level=$2 ./semver.sh)"
  new="${new:8}"
  [[ "$new" == "$3" ]] \
  && printf "\033[32m-" \
  || printf "\033[31mx"
  echo " { prev: $1, level: $2, expected: $3, got: $new }"
  printf "\033[0m"
}

t 0.0.1         0 1.0.0
t 0.0.1         1 0.1.0
t 0.0.1         2 0.0.2

t 0.0.1         3 0.0.2-alpha.0
t 0.0.1-alpha   3 0.0.1-beta.0
t 0.0.1-alpha.5 3 0.0.1-beta.0
t 0.0.1-beta.1  3 0.0.1-rc.0
t 0.0.1-rc.5    3 0.0.2-alpha.0
t 0.0.1-rc      3 0.0.2-alpha.0

t 0.0.1         4 0.0.2-alpha.0
t 0.0.1-alpha   4 0.0.1-alpha.1
t 0.0.1-alpha.5 4 0.0.1-alpha.6
t 0.0.1-beta.1  4 0.0.1-beta.2
t 0.0.1-rc.5    4 0.0.1-rc.6

exit










  [[ $level == 4 ]] && [[ ${ver[3]} == 0 ]]
  [[ $level == 3 ]] && [[ ${ver[$level]} == 1 ]] \
  && ver[2]=$(( ${ver[2]}+1 )) # we also step to next patch
  [[ $level == 3 ]]  && [ ! -v "pre[${ver[$level]}]" ] \
  && level=2 # we step to next patch
