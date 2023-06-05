# :blue_book: Installation

The library is published on [jitpack.io](https://jitpack.io).

The custom domain `com.open-meteo` used as group id is an alias to `com.github.open-meteo`.

## Gradle

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
	implementation 'com.open-meteo:open-meteo-api-kotlin:0.6.0-alpha.1'
}
```

## Android

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
	implementation 'com.open-meteo:open-meteo-api-kotlin:0.6.0-alpha.1'
}
```

## Maven

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
	<version>0.6.0-alpha.1</version>
</dependency>
```
