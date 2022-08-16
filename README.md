# ☁️ Open-Meteo Kotlin Library

[![Kotlin Stability: Alpha](https://img.shields.io/badge/stability-alpha-kotlin.svg?color=DB3683&logo=kotlin&logoColor=ffffff&logoWidth=12&style=for-the-badge)](https://kotlinlang.org/docs/components-stability.html)&emsp;
[![GitHub license: MIT](https://img.shields.io/github/license/open-meteo/open-meteo-api-kotlin?logo=github&logoWidth=14&style=for-the-badge)](LICENSE.md)&emsp;
[![Jitpack.io Version](https://img.shields.io/jitpack/v/github/open-meteo/open-meteo-api-kotlin?logo=data:image/svg%2bxml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCA1MTIgNTEyIj4mZ3Q7PHBhdGggZD0iTTMxNi43OCAyMi44NzVjLTM5LjkzNCA3LjczLTY4LjE2NiAyMy41ODctODUuMDYgNDUuNTk0bDU2LjY4NiAzMi43MThjNy4wODItMy4zNjYgMTQuODUyLTUuMjg4IDIyLjk3LTUuNDA3IDQuNi0uMDY2IDkuMzEyLjQ0NyAxNC4wNjIgMS41OTUgMy41NS0yMS40NTIuOTMtNDYuMzgyLTguNjU3LTc0LjV6bS05Ny4xNTUgNjAuMTg4LTg2Ljk3IDE1MC41NjIgOTMuMTI2IDUzLjgxMyAyMS44NzYtMzcuODc1Yy05LjkzLTkuNzk0LTE0LjA4LTI0LjY5NS0xNC41OTQtNDAuMjItLjYzLTE4Ljk4NiAzLjk4LTQwLjA5OCAxMi41NjMtNTkuMTg3IDYuMzctMTQuMTY3IDE0LjkxLTI3LjI5NCAyNS43Mi0zNy4yMmwtNTEuNzItMjkuODc0ek00MjYuMDk1IDg2Yy0yOS4zOTQgNS42OS01Mi40MjMgMTUuNzk1LTY5LjI4MyAyOS41IDkuMzMgOS44IDE0LjMwMiAyMS43NTggMTUuMjgyIDM0LjAzbDU2LjUzIDMyLjYyNmMxMC42OS0yNS42NzggMTAuNDgzLTU3Ljk5LTIuNTMtOTYuMTU2ek02NC45NjggOTQuMDYzIDI3LjAzIDE1OS43OGwxMDIgNDIuNzUgNjAuODE0LTEwNS4zMUw2NC45NyA5NC4wNjJ6bTI0Ni42MjQgMTkuOTY4Yy02LjUzNS4xMDUtMTIuNTkgMi4yNi0xOC41NjMgNS44NDUtMTEuOTQyIDcuMTctMjIuODc2IDIxLjMzMi0zMC4zNDIgMzcuOTM4LTcuNDY3IDE2LjYwNS0xMS40OCAzNS41MTItMTAuOTcgNTAuOTA2LjUxIDE1LjM3NyA1LjMyMyAyNi4wNjQgMTIuODEzIDMwLjQwNS4wMDguMDA0LjAyNi0uMDA0LjAzMyAwIDcuNTAzIDQuMzE2IDE5LjE1IDMuMTUyIDMyLjcxOC00LjA5NCAxMy41ODQtNy4yNTMgMjcuOTUtMjAuMTY3IDM4LjU5NS0zNC45MzYgMTAuNjQ1LTE0Ljc3IDE3LjQ1LTMxLjM1IDE3LjY4OC00NS4yOC4yMzctMTMuOTMzLTQuNjgzLTI1LjI0Mi0yMC41LTM0LjM3Ni03LjkxLTQuNTY4LTE0LjkzNS02LjUxLTIxLjQ3LTYuNDA3em01OC44MTIgNTYuMTI2Yy0zLjE5OCAxNC4zMTUtMTAuMjk3IDI4LjI4LTE5LjM3NSA0MC44NzUtMTIuMjM2IDE2Ljk4LTI4LjE4IDMxLjUyMi00NC45MzYgNDAuNDctMTMuNzQgNy4zMzgtMjguNzk1IDExLjItNDIuMjggNy40MzhsLTIxLjg0NSAzNy44MTIgOTMuMTI0IDUzLjc4TDQyMiAxOTkuOTRsLTUxLjU5NC0yOS43ODJ6bTU0LjIyIDYyLjYyNS02MC43ODIgMTA1LjM0NSA4OC4wMyA2NyAzNy45MzgtNjUuNzUtNjUuMTg3LTEwNi41OTR6bS0yNzYuNjU3IDMxLjI4MmMtMjIuNDc3IDkuODQtMzkuNzMgMjMuMTQ4LTUxLjgxNCAzOS4zNDRsODcuNSA1MC41M2M4LjA0LTE4LjU4IDExLjA1Mi00MC4wOTggOC41LTY0LjM0MmwtNDQuMTg3LTI1LjUzek04Ny44NzQgMzIwLjIyIDE4LjIyIDQ0MC43OHYzNy4zNDVsODUuODQzLTE0OC41NjMtMTYuMTg4LTkuMzQzem0xNjkuNDA2IDYuOTY3Yy0yMi40OCA5Ljg0LTM5LjcyNSAyMy4xNDMtNTEuODEgMzkuMzQ0bDg3LjUgNTAuNTMzYzguMDQyLTE4LjU5IDExLjA1Ni00MC4xMiA4LjUtNjQuMzc1bC00NC4xOS0yNS41em0tMTM1LjA5IDEyLjg0NC05MCAxNTQuODc2aDIxLjYybDg0LjU5NC0xNDUuNS0xNi4yMi05LjM3NXptMzQuNDM3IDE5Ljg3Ni03Ny43OCAxMzVoMjEuNTZsNzIuMzc2LTEyNS42NTYtMTYuMTU1LTkuMzQ0ek0xOTcuNDcgMzgzLjVsLTYzLjIyIDExMS40MDZoMjEuNWw1Ny45MDYtMTAyLjA2Mi0xNi4xODctOS4zNDR6bTM0LjQzNiAxOS45MDYtNTEuMDYyIDkxLjVoMjEuMzc1bDQ1Ljg3NC04Mi4xODctMTYuMTg4LTkuMzE0em0zNC40MzggMTkuODc1LTM4LjkzOCA3MS42MjZoMjEuMzEzbDMzLjg0My02Mi4yOC0xNi4yMi05LjM0NXoiIGZpbGw9IiNmZmYiLz48L3N2Zz4=&logoWidth=14&style=for-the-badge)](https://jitpack.io/#com.open-meteo/open-meteo-api-kotlin)
<!-- shields.io allows only vcs packages, what about custom organizations domains? -->

A Kotlin library for the [Open-Meteo.com](https://open-meteo.com) APIs.

This library would be (nearly) useless without the public API servers: please, consider [sponsoring open-meteo on GitHub](https://github.com/sponsors/open-meteo).

---

## ⚠️ Warning

Because it's still in early development, some breaking changes **WILL** occur.
Using the library is discouraged until a version 1.0.0 is published: single commits could break code and remove some features.

---

## 📑 Index

 - [📘 Installation](#📘-Installation)
   - [Gradle](#Gradle)
   - [Android (gradle)](#Android)
   - [Maven](#Maven)
 - [📗 Usage](#📗-Usage)
 - [🔬 Analysis](#🔬-Analysis)
 - [📝 TODO](#📝-TODO)
 - [⚖️ Legal](#⚖️-Legal)

## 📘 Installation

The library is published on [jitpack.io](https://jitpack.io).
Please note that a custom domain for the group id is used: it's an "alias" for `com.github.open-meteo`.

### Gradle

1. Add the JitPack repository to your root build.gradle file:

```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

2. Add the dependency:

```gradle
dependencies {
	implementation 'com.open-meteo:open-meteo-api-kotlin:0.0.2-beta.2'
}
```

### Android

1. Add the JitPack repository to your settings.gradle file:

```gradle
dependencyResolutionManagement {
	...
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

2. Add the dependency:

```gradle
dependencies {
	implementation 'com.open-meteo:open-meteo-api-kotlin:0.0.2-beta.2'
}
```

### Maven

1. Add the JitPack repository to your build file:

```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

2. Add the dependency:

```xml
<dependency>
	<groupId>com.open-meteo</groupId>
	<artifactId>open-meteo-api-kotlin</artifactId>
	<version>0.0.2-beta.2</version>
</dependency>
```

## 📗 Usage

Here's a quick example:
```kotlin
import com.openmeteo.api.forecast.ForecastEndpoint
import com.openmeteo.api.forecast.params.*

val forecastEndpoint = ForecastEndpoint()
// you can call the invoke method with `()`
val response = forecastEndpoint(
	hourly = listOf(
		Hourly.weathercode,
		Hourly.temperature_2m,
	),
)
val hourly = response.hourly!! // don't throw in production, this is just an example
val weatherCode = hourly.weathercode!! 
println(weatherCode[0])
```

Please note that some arrays may be filled with `null`s, hence you might want to use `?.get(0)` instead.
Research is going to be done to find out which array could have `null`s and reduce uncertainties.

The library will include some useful examples in the tests and the documentation.

## 🔬 Analysis

### Versioning

The [Semantic Versioning](https://semver.org/) standard is used.

## 📝 TODO

[A dedicated Github project](https://github.com/users/DadiBit/projects/1) is used to keep organized.

## ⚖️ Legal

You are free to do pretty much whatever you want with this library, as long as you honour the [LICENSE](LICENSE.md) and the [ATTRIBUTIONS](ATTRIBUTIONS.md).

