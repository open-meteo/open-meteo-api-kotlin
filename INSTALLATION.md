# 📘 Installation

The library is published on [jitpack.io](https://jitpack.io).
Please note that a custom domain for the group id is used: it's an "alias" for `com.github.open-meteo`.

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
	implementation 'com.open-meteo:open-meteo-api-kotlin:0.0.2-beta.2'
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
	implementation 'com.open-meteo:open-meteo-api-kotlin:0.0.2-beta.2'
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
	<version>0.0.2-beta.2</version>
</dependency>
```