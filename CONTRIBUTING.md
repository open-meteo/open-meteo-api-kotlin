# Contributing to the Open-Meteo API Kotlin library

This document includes a set of guidelines and rules for contributing to the library. Feel free to open an issue if you have any doubts.

## Issues

Issues should be used to report bugs and request enhancements/features.

## Emojis

Although unicode emojis are accepted, [github codes](https://github.com/ikatyang/emoji-cheat-sheet/blob/master/README.md) are preferred: unicode could break in some editors.

## Versioning

A stricter variation of the [Semantic Versioning 2.0.0](https://semver.org/spec/v2.0.0.html) standard is used.

 - The main version name should be `<MAJOR>.<MINOR>.<PATCH>`
 - An optional `-<NAME>` or `-<NAME>.<ID>` pre-release suffix can be used after the main version name
 - Absolute integers must not start with `0`, other than `0` itself
 - `<MAJOR>`, `<MINOR>` and `<PATCH>` must be absolute integers
 - Pre-release `<NAME>`, if present, must be `alpha`, `beta` or `rc` (release candidate)
 - Pre-release `<ID>`, if present, must be an absolute integer

Some valid tags are: `0.0.0`, `0.2.0-beta`, `1.0.0-alpha.0`, `314.159.265.-alpha.3589793`, `9999.0.0-rc.1`
You can validate a version tag with this regex: `^((0|[1-9][0-9]*)\.){2}(0|[1-9][0-9]*)(-(alpha|beta|rc)(\.(0|[1-9][0-9]*))?)?$`
Please note that this regex is also used in the github "Release" workflow to verify the actor "Version" input.
