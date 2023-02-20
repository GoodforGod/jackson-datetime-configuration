# Jackson DateTime Configuration

![Java CI](https://github.com/GoodforGod/jackson-datetime-configuration/workflows/Java%20CI/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_jackson-datetime-configuration&metric=alert_status)](https://sonarcloud.io/dashboard?id=GoodforGod_jackson-datetime-configuration)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_jackson-datetime-configuration&metric=coverage)](https://sonarcloud.io/dashboard?id=GoodforGod_jackson-datetime-configuration)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=GoodforGod_jackson-datetime-configuration&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=GoodforGod_jackson-datetime-configuration)

Jackson DateTime module for [java.time.*](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/package-summary.html) package with proper Configuration.

Library try to fix poor [standard Jackson JSR310 DateTime module](https://github.com/FasterXML/jackson-modules-java8/blob/2.14/datetime/src/main/java/com/fasterxml/jackson/datatype/jsr310/JavaTimeModule.java),
now you can change formatters for Date/Times
and library is using [ISO8601 with millis precision](https://goodforgod.dev/posts/2/) formatters by default.

Features:
- Default configuration with formatters [ISO8601 with millis precision](https://goodforgod.dev/posts/2/)
- Module configuration with getter\setters (can easily be used in framework configurations)

## Dependency :rocket:

Compatible with Java 8+.

**Gradle**
```groovy
dependencies {
    implementation "io.goodforgod:jackson-datetime-configuration:2.0.0"
}
```

**Maven**
```xml
<dependency>
    <groupId>io.goodforgod</groupId>
    <artifactId>jackson-datetime-configuration</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Configuration

JavaTimeModule library provides uses same [Package.VERSION](https://github.com/GoodforGod/jackson-datetime-configuration/blob/master/src/main/java/io/goodforgod/jackson/module/datetime/configuration/JavaTimeModule.java#L41)
as [Jackson Module](https://github.com/FasterXML/jackson-modules-java8/blob/2.14/datetime/src/main/java/com/fasterxml/jackson/datatype/jsr310/JavaTimeModule.java#L114), 
this is done on purpose cause modules are exclusive.

Example how to create JavaTimeModuleConfiguration with [ISO8601 with millis precision](https://goodforgod.dev/posts/2/) formatters:
```java
JavaTimeModuleConfiguration configuration = new JavaTimeModuleConfiguration();
```

Example of all available configurations for JavaTimeModuleConfiguration.
```java
JavaTimeModuleConfiguration configuration = new JavaTimeModuleConfiguration()
                    .setInstantFormat("uuuu-MM-dd")                                     // Set Instant formatter
                    .setOffsetTimeFormat("HH:mm:ss[.SSS]XXX")                           // Set OffsetTime formatter
                    .setOffsetDateTimeFormat("uuuu-MM-dd'T'HH:mm:ss[.SSS]XXX")          // Set OffsetDateTime formatter
                    .setZonedDateTimeFormat("uuuu-MM-dd'T'HH:mm:ss[.SSS]XXX['['VV']']") // Set ZonedDateTime formatter
                    .setLocalDateTimeFormat("uuuu-MM-dd'T'HH:mm:ss[.SSS]")              // Set LocalDateTime formatter
                    .setLocalDateFormat("uuuu-MM-dd")                                   // Set LocalDate formatter
                    .setLocalTimeFormat("HH:mm:ss[.SSS]XXX")                            // Set LocalTime formatter
                    .setYearFormat("uuuu")                                              // Set Year formatter
                    .setYearMonthFormat("uuuu-MM")                                      // Set YearMonth formatter
                    .setMonthDayFormat("MM-dd")                                         // Set MonthDay formatter
                    .setForceIsoChronology(true)                                        // Forces IsoChronology for all formatters
                    .setForceResolverStrict(true);                                      // Forces ResolverStyle#STRICT for all formatters
```

### Module

Example how to create configuration, module and register module for ObjectMapper.

```java
JavaTimeModuleConfiguration configuration = new JavaTimeModuleConfiguration();
configuration.setLocalTimeFormat("HH-mm-ss");

JavaTimeModule module = configuration.getModule();

ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(module);
```

### Hints

For proper Date & Times serialization, you probably don't want to adjust ZoneOffset when deserializing and serializing as longs, then configure ObjectMapper as follows:
```java
ObjectMapper mapper = new ObjectMapper()
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)               // Don't write dates as longs
        .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);    // Don't change ZoneOffset to local
```

## License

This project licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

