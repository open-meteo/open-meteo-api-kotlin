#!/bin/bash

changes() {
  h="[0-9a-f]"
  alt="\`$h{7}\`"
  url="\.\.\/\.\.\/commit\/$h{40}"
  list=$(sed -nE "s/^( - \[$alt\]\($url\) - )$2: /\1/p" commits)
  if [[ -n "$list" ]]; then
    echo "## $1"
    echo
    echo "$list"
    echo
  fi
}

git log "$(git describe --tags --abbrev=0 @^)"..@ \
--pretty=format:" - [\`%h\`](../../commit/%H) - %s" > commits

echo "body<<EOF"
changes ":rocket: New features" "feat"
changes ":bug: Fixes" "fix"
changes ":orange_book: Docs" "docs"
changes ":test_tube: Tests" "test"
#changes "Refactor" "refactor"
#changes "Style changes" "style"
#changes "Chore" "chore"
changes ":hammer: Build" "build"
changes ":leftwards_arrow_with_hook: Reverted commits" "revert"
echo "EOF"
