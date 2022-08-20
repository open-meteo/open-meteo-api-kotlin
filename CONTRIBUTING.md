# Contributing to the Open-Meteo API Kotlin library

This document includes a set of guidelines and rules for contributing to the library.
Feel free to open an issue if you have any doubts.

## Issues

Issues should be used to report bugs and request enhancements/features.

## Emojis

Although unicode emojis are accepted, [github codes](https://github.com/ikatyang/emoji-cheat-sheet/blob/master/README.md) are preferred: unicode could break in some editors.

## Versioning

A stricter variation of the [Semantic Versioning 2.0.0](https://semver.org/spec/v2.0.0.html) standard is used.

 - The version id should be `<major>.<minor>.<patch>[-<name>[.<id>]]`
 - Absolute integers must not start with `0`, other than `0` itself
 - `<major>`, `<minor>` and `<patch>` must be absolute integers
 - Pre-release `<name>`, if present, must be `alpha`, `beta` or `rc` (release candidate)
 - Pre-release `<id>`, if present, must be an absolute integer

Some valid tags are: `0.0.0`, `0.2.0-beta`, `1.0.0-alpha.0`, `314.159.265.-alpha.3589793`, `9999.0.0-rc.1`
You can validate a version tag with this regex: `^((0|[1-9][0-9]*)\.){2}(0|[1-9][0-9]*)(-(alpha|beta|rc)(\.(0|[1-9][0-9]*))?)?$`
Please note that this regex is also used in the github "Release" workflow to verify the "Version" input.

## Commits

### Files

> :warning: The project is still in early development: expect commits breaking code anyway

A commit shall not break any code. If possible, it should only contain related changes:

 - using a new naming convention
 - updating the library version in multiple files

### Message

A stricter variation of the [Conventional Commits 1.0.0](https://www.conventionalcommits.org/en/v1.0.0/) standard is used.
The commit message should look like this:
```
<type>[!]: <description>

[body]
```

 - The first line is a brief (< 50 characters, if possible) description of what changed.
 - The `<type>` should be one of:
   - `feat` adds a new feature (bump `<minor>`)
   - `fix` fixes a bug (bump `<patch>`)
   - `refactor` refactors code (eg: move code into a private method)
   - `style` changes whitespaces, new-lines, scoped variables renaming
   - `docs` updates documentation (README, GitHub wiki, or other markdown files)
   - `test` adds or updates tests
   - `chore` changes not source or test code (eg: update dependencies)
   - `build` changes the build process (eg: new dependencies)
   - `revert` reverts a previous commit
 - `!` can be placed after `<type>` if the commit includes breaking changes (bump `<major>`)

If the `<type>` field is not valid/missing, the commit won't be used to automate changelogs.
After the first major release, a new major release must be released if breaking changes are introduced.
