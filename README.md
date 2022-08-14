# ☁️ Open-Meteo Kotlin Library

[![Kotlin Alpha](https://kotl.in/badges/alpha.svg)](https://kotlinlang.org/docs/components-stability.html)
[![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE)
<!--[![Release](https://jitpack.io/v/open-meteo/open-meteo-api-kotlin.svg)](https://jitpack.io/#open-meteo/open-meteo-api-kotlin)-->

A Kotlin library for the [Open-Meteo.com](https://open-meteo.com) APIs.

This library would be (nearly) useless without the public API servers: please, consider [sponsoring open-meteo on GitHub](https://github.com/sponsors/open-meteo).

---

## ⚠️ Warning

Because it's still in early development, some breaking changes **WILL** occur. I highly discourage to use the library until a beta stage is reached.

---

## 📑 Index

 - [📘 Installation](#📘-Installation)
 - [📗 Usage](#📗-Usage)
 - [🔬 Analysis](#🔬-Analysis)
 - [📝 TODO](#📝-TODO)
 - [⚖️ Legal](#⚖️-Legal)

---

## 📘 Installation

Publishing the library on [jitpack.io](https://jitpack.io) is [planned](https://github.com/users/DadiBit/projects/1) in the (really) near future.
As stated in the warning, the library is still being developed and designed, hence it's only distributed through this repo, for the time being.

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
).use {
	// the endpoints return only the data stream, currently
	it.bufferedReader().readText()
}
println(response)
```

The library will include some useful examples in the tests and the documentation.

## 🔬 Analysis

### Versioning

The [Semantic Versioning](https://semver.org/) standard is used.

## 📝 TODO

[A dedicated Github project](https://github.com/users/DadiBit/projects/1) is used to keep organized.
It's easy to keep track of issues status and priority.

## ⚖️ Legal

You are free to do pretty much whatever you want with this library, as long as you honour the [LICENSE](LICENSE) and the [ATTRIBUTIONS](ATTRIBUTIONS.md).

