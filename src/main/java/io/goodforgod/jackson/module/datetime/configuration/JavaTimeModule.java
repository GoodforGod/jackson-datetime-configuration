package io.goodforgod.jackson.module.datetime.configuration;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedClassResolver;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.*;
import com.fasterxml.jackson.datatype.jsr310.deser.key.*;
import com.fasterxml.jackson.datatype.jsr310.ser.*;
import com.fasterxml.jackson.datatype.jsr310.ser.key.ZonedDateTimeKeySerializer;
import io.goodforgod.jackson.module.datetime.configuration.deserializer.Deserializers;
import io.goodforgod.jackson.module.datetime.configuration.deserializer.InstantISODeserializer;
import io.goodforgod.jackson.module.datetime.configuration.deserializer.OffsetTimeISODeserializer;
import io.goodforgod.jackson.module.datetime.configuration.serializer.InstantISOSerializer;
import io.goodforgod.jackson.module.datetime.configuration.serializer.OffsetDateTimeISOSerializer;
import io.goodforgod.jackson.module.datetime.configuration.serializer.OffsetTimeISOSerializer;
import io.goodforgod.jackson.module.datetime.configuration.serializer.Serializers;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Based on original Jackson {@link JavaTimeModule}
 *
 * @author Anton Kurako (GoodforGod)
 * @since 11.01.2022
 */
public class JavaTimeModule extends SimpleModule {

    public JavaTimeModule(DateTimeFormatter instantFormat,
                          DateTimeFormatter offsetTimeFormat,
                          DateTimeFormatter offsetDateTimeFormat,
                          DateTimeFormatter zonedDateTimeFormat,
                          DateTimeFormatter localDateTimeFormat,
                          DateTimeFormatter localDateFormat,
                          DateTimeFormatter localTimeFormat,
                          DateTimeFormatter yearFormat,
                          DateTimeFormatter yearMonthFormat,
                          DateTimeFormatter monthDayFormat) {
        super(PackageVersion.VERSION);

        // First deserializers
        final InstantDeserializer<Instant> instantDeserializer = getInstantDeserializer(instantFormat);
        final OffsetTimeDeserializer offsetTimeDeserializer = getOffsetTimeDeserializer(offsetTimeFormat);
        final InstantDeserializer<OffsetDateTime> offsetDateTimeDeserializer = getOffsetDateTimeDeserializer(
                offsetDateTimeFormat);
        final InstantDeserializer<ZonedDateTime> zonedDateTimeDeserializer = getZonedDateTimeDeserializer(zonedDateTimeFormat);
        final LocalDateTimeDeserializer localDateTimeDeserializer = getLocalDateTimeDeserializer(localDateTimeFormat);
        final LocalDateDeserializer localDateDeserializer = getLocalDateDeserializer(localDateFormat);
        final LocalTimeDeserializer localTimeDeserializer = getLocalTimeDeserializer(localTimeFormat);
        final YearDeserializer yearDeserializer = getYearDeserializer(yearFormat);
        final YearMonthDeserializer yearMonthDeserializer = getYearMonthDeserializer(yearMonthFormat);
        final MonthDayDeserializer monthDayDeserializer = getMonthDayDeserializer(monthDayFormat);

        addDeserializer(Instant.class, instantDeserializer);
        addDeserializer(OffsetDateTime.class, offsetDateTimeDeserializer);
        addDeserializer(ZonedDateTime.class, zonedDateTimeDeserializer);
        addDeserializer(OffsetTime.class, offsetTimeDeserializer);
        addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        addDeserializer(LocalDate.class, localDateDeserializer);
        addDeserializer(LocalTime.class, localTimeDeserializer);
        addDeserializer(Year.class, yearDeserializer);
        addDeserializer(YearMonth.class, yearMonthDeserializer);
        addDeserializer(MonthDay.class, monthDayDeserializer);

        // Other deserializers
        addDeserializer(Period.class, JSR310StringParsableDeserializer.PERIOD);
        addDeserializer(Duration.class, DurationDeserializer.INSTANCE);
        addDeserializer(ZoneId.class, JSR310StringParsableDeserializer.ZONE_ID);
        addDeserializer(ZoneOffset.class, JSR310StringParsableDeserializer.ZONE_OFFSET);

        // then serializers:
        final InstantSerializer instantSerializer = getInstantSerializer(instantFormat);
        final OffsetTimeSerializer offsetTimeSerializer = getOffsetTimeSerializer(offsetTimeFormat);
        final OffsetDateTimeSerializer offsetDateTimeSerializer = getOffsetDateTimeSerializer(offsetDateTimeFormat);
        final ZonedDateTimeSerializer zonedDateTimeSerializer = getZonedDateTimeSerializer(zonedDateTimeFormat);
        final LocalDateTimeSerializer localDateTimeSerializer = getLocalDateTimeSerializer(localDateTimeFormat);
        final LocalDateSerializer localDateSerializer = getLocalDateSerializer(localDateFormat);
        final LocalTimeSerializer localTimeSerializer = getLocalTimeSerializer(localTimeFormat);
        final YearSerializer yearSerializer = getYearSerializer(yearFormat);
        final YearMonthSerializer yearMonthSerializer = getYearMonthSerializer(yearMonthFormat);
        final MonthDaySerializer monthDaySerializer = getMonthDaySerializer(monthDayFormat);

        addSerializer(Instant.class, instantSerializer);
        addSerializer(OffsetDateTime.class, offsetDateTimeSerializer);
        addSerializer(ZonedDateTime.class, zonedDateTimeSerializer);
        addSerializer(OffsetTime.class, offsetTimeSerializer);
        addSerializer(LocalDateTime.class, localDateTimeSerializer);
        addSerializer(LocalDate.class, localDateSerializer);
        addSerializer(LocalTime.class, localTimeSerializer);
        addSerializer(Year.class, yearSerializer);
        addSerializer(YearMonth.class, yearMonthSerializer);
        addSerializer(MonthDay.class, monthDaySerializer);

        // Other serializers
        addSerializer(Period.class, Serializers.PERIOD);
        addSerializer(Duration.class, DurationSerializer.INSTANCE);
        addSerializer(ZoneId.class, Serializers.ZONE_ID);
        addSerializer(ZoneOffset.class, Serializers.ZONE_OFFSET);

        // key serializers
        addKeySerializer(ZonedDateTime.class, ZonedDateTimeKeySerializer.INSTANCE);

        // key deserializers
        addKeyDeserializer(ZonedDateTime.class, ZonedDateTimeKeyDeserializer.INSTANCE);
        addKeyDeserializer(LocalDateTime.class, LocalDateTimeKeyDeserializer.INSTANCE);
        addKeyDeserializer(LocalDate.class, LocalDateKeyDeserializer.INSTANCE);
        addKeyDeserializer(LocalTime.class, LocalTimeKeyDeserializer.INSTANCE);
        addKeyDeserializer(OffsetDateTime.class, OffsetDateTimeKeyDeserializer.INSTANCE);
        addKeyDeserializer(OffsetTime.class, OffsetTimeKeyDeserializer.INSTANCE);

        // Other key deserializers
        addKeyDeserializer(Duration.class, DurationKeyDeserializer.INSTANCE);
        addKeyDeserializer(Instant.class, InstantKeyDeserializer.INSTANCE);
        addKeyDeserializer(MonthDay.class, MonthDayKeyDeserializer.INSTANCE);
        addKeyDeserializer(Period.class, PeriodKeyDeserializer.INSTANCE);
        addKeyDeserializer(Year.class, YearKeyDeserializer.INSTANCE);
        addKeyDeserializer(YearMonth.class, YearMonthKeyDeserializer.INSTANCE);
        addKeyDeserializer(ZoneId.class, ZoneIdKeyDeserializer.INSTANCE);
        addKeyDeserializer(ZoneOffset.class, ZoneOffsetKeyDeserializer.INSTANCE);
    }

    protected InstantDeserializer<Instant> getInstantDeserializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_INSTANT == formatter) {
            return InstantDeserializer.INSTANT;
        } else {
            return new InstantISODeserializer<>(InstantDeserializer.INSTANT, formatter);
        }
    }

    protected InstantDeserializer<OffsetDateTime> getOffsetDateTimeDeserializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_OFFSET_DATE_TIME == formatter) {
            return Deserializers.JAVA_ISO_OFFSET_DATE_TIME;
        } else if (DateTimeFormatters.ISO_OFFSET_DATE_TIME == formatter) {
            return Deserializers.OFFSET_DATE_TIME;
        } else {
            return new InstantISODeserializer<>(InstantDeserializer.OFFSET_DATE_TIME, formatter);
        }
    }

    protected InstantDeserializer<ZonedDateTime> getZonedDateTimeDeserializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_ZONED_DATE_TIME == formatter) {
            return Deserializers.JAVA_ISO_ZONED_DATE_TIME;
        } else if (DateTimeFormatters.ISO_ZONED_DATE_TIME == formatter) {
            return Deserializers.ZONED_DATE_TIME;
        } else {
            return new InstantISODeserializer<>(InstantDeserializer.ZONED_DATE_TIME, formatter);
        }
    }

    protected OffsetTimeDeserializer getOffsetTimeDeserializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_OFFSET_TIME == formatter) {
            return OffsetTimeDeserializer.INSTANCE;
        } else if (DateTimeFormatters.ISO_OFFSET_TIME == formatter) {
            return Deserializers.OFFSET_TIME;
        } else {
            return new OffsetTimeISODeserializer(formatter);
        }
    }

    protected LocalDateTimeDeserializer getLocalDateTimeDeserializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_LOCAL_DATE_TIME == formatter) {
            return LocalDateTimeDeserializer.INSTANCE;
        } else if (DateTimeFormatters.ISO_LOCAL_DATE_TIME == formatter) {
            return Deserializers.LOCAL_DATE_TIME;
        } else {
            return new LocalDateTimeDeserializer(formatter);
        }
    }

    protected LocalDateDeserializer getLocalDateDeserializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_LOCAL_DATE == formatter) {
            return LocalDateDeserializer.INSTANCE;
        } else if (DateTimeFormatters.ISO_LOCAL_DATE == formatter) {
            return Deserializers.LOCAL_DATE;
        } else {
            return new LocalDateDeserializer(formatter);
        }
    }

    protected LocalTimeDeserializer getLocalTimeDeserializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_LOCAL_TIME == formatter) {
            return LocalTimeDeserializer.INSTANCE;
        } else if (DateTimeFormatters.ISO_LOCAL_TIME == formatter) {
            return Deserializers.LOCAL_TIME;
        } else {
            return new LocalTimeDeserializer(formatter);
        }
    }

    protected YearDeserializer getYearDeserializer(DateTimeFormatter formatter) {
        if (DateTimeFormatters.ISO_LOCAL_DATE_TIME == formatter) {
            return Deserializers.YEAR;
        } else {
            return new YearDeserializer(formatter);
        }
    }

    protected YearMonthDeserializer getYearMonthDeserializer(DateTimeFormatter formatter) {
        if (DateTimeFormatters.ISO_LOCAL_DATE == formatter) {
            return Deserializers.YEAR_MONTH;
        } else {
            return new YearMonthDeserializer(formatter);
        }
    }

    protected MonthDayDeserializer getMonthDayDeserializer(DateTimeFormatter formatter) {
        if (DateTimeFormatters.ISO_LOCAL_TIME == formatter) {
            return Deserializers.MONTH_DAY;
        } else {
            return new MonthDayDeserializer(formatter);
        }
    }

    protected InstantSerializer getInstantSerializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_INSTANT == formatter) {
            return InstantSerializer.INSTANCE;
        } else {
            return new InstantISOSerializer(formatter);
        }
    }

    protected OffsetDateTimeSerializer getOffsetDateTimeSerializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_OFFSET_DATE_TIME == formatter) {
            return OffsetDateTimeSerializer.INSTANCE;
        } else if (DateTimeFormatters.ISO_OFFSET_DATE_TIME == formatter) {
            return Serializers.OFFSET_DATE_TIME;
        } else {
            return new OffsetDateTimeISOSerializer(formatter);
        }
    }

    protected ZonedDateTimeSerializer getZonedDateTimeSerializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_ZONED_DATE_TIME == formatter) {
            return Serializers.JAVA_ISO_ZONED_DATE_TIME;
        } else if (DateTimeFormatters.ISO_ZONED_DATE_TIME == formatter) {
            return Serializers.ZONED_DATE_TIME;
        } else {
            return new ZonedDateTimeSerializer(formatter);
        }
    }

    protected OffsetTimeSerializer getOffsetTimeSerializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_OFFSET_TIME == formatter) {
            return Serializers.JAVA_ISO_OFFSET_TIME;
        } else if (DateTimeFormatters.ISO_OFFSET_TIME == formatter) {
            return Serializers.OFFSET_TIME;
        } else {
            return new OffsetTimeISOSerializer(formatter);
        }
    }

    protected LocalDateTimeSerializer getLocalDateTimeSerializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_LOCAL_DATE_TIME == formatter) {
            return LocalDateTimeSerializer.INSTANCE;
        } else if (DateTimeFormatters.ISO_LOCAL_DATE_TIME == formatter) {
            return Serializers.LOCAL_DATE_TIME;
        } else {
            return new LocalDateTimeSerializer(formatter);
        }
    }

    protected LocalDateSerializer getLocalDateSerializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_LOCAL_DATE == formatter) {
            return LocalDateSerializer.INSTANCE;
        } else if (DateTimeFormatters.ISO_LOCAL_DATE == formatter) {
            return Serializers.LOCAL_DATE;
        } else {
            return new LocalDateSerializer(formatter);
        }
    }

    protected LocalTimeSerializer getLocalTimeSerializer(DateTimeFormatter formatter) {
        if (DateTimeFormatter.ISO_LOCAL_TIME == formatter) {
            return LocalTimeSerializer.INSTANCE;
        } else if (DateTimeFormatters.ISO_LOCAL_TIME == formatter) {
            return Serializers.LOCAL_TIME;
        } else {
            return new LocalTimeSerializer(formatter);
        }
    }

    protected YearSerializer getYearSerializer(DateTimeFormatter formatter) {
        if (DateTimeFormatters.ISO_LOCAL_DATE_TIME == formatter) {
            return Serializers.YEAR;
        } else {
            return new YearSerializer(formatter);
        }
    }

    protected YearMonthSerializer getYearMonthSerializer(DateTimeFormatter formatter) {
        if (DateTimeFormatters.ISO_LOCAL_DATE == formatter) {
            return Serializers.YEAR_MONTH;
        } else {
            return new YearMonthSerializer(formatter);
        }
    }

    protected MonthDaySerializer getMonthDaySerializer(DateTimeFormatter formatter) {
        if (DateTimeFormatters.ISO_LOCAL_TIME == formatter) {
            return Serializers.MONTH_DAY;
        } else {
            return new MonthDaySerializer(formatter);
        }
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.addValueInstantiators(new ValueInstantiators.Base() {

            @Override
            public ValueInstantiator findValueInstantiator(DeserializationConfig config,
                                                           BeanDescription beanDesc,
                                                           ValueInstantiator defaultInstantiator) {
                JavaType type = beanDesc.getType();
                Class<?> raw = type.getRawClass();

                // 15-May-2015, tatu: In theory not safe, but in practice we do need to do "fuzzy" matching
                // because we will (for now) be getting a subtype, but in future may want to downgrade
                // to the common base type. Even more, serializer may purposefully force use of base type.
                // So... in practice it really should always work, in the end. :)
                if (ZoneId.class.isAssignableFrom(raw)) {
                    // let's assume we should be getting "empty" StdValueInstantiator here:
                    if (defaultInstantiator instanceof StdValueInstantiator) {
                        StdValueInstantiator inst = (StdValueInstantiator) defaultInstantiator;
                        // one further complication: we need ZoneId info, not sub-class
                        AnnotatedClass ac;
                        if (raw == ZoneId.class) {
                            ac = beanDesc.getClassInfo();
                        } else {
                            // we don't need Annotations, so constructing directly is fine here
                            // even if it's not generally recommended
                            ac = AnnotatedClassResolver.resolve(config,
                                    config.constructType(ZoneId.class), config);
                        }
                        if (!inst.canCreateFromString()) {
                            AnnotatedMethod factory = _findFactory(ac, "of", String.class);
                            if (factory != null) {
                                inst.configureFromStringCreator(factory);
                            }
                            // otherwise... should we indicate an error?
                        }
                        // return ZoneIdInstantiator.construct(config, beanDesc, defaultInstantiator);
                    }
                }
                return defaultInstantiator;
            }
        });
    }

    protected AnnotatedMethod _findFactory(AnnotatedClass cls, String name, Class<?>... argTypes) {
        final int argCount = argTypes.length;
        for (AnnotatedMethod method : cls.getFactoryMethods()) {
            if (!name.equals(method.getName()) || (method.getParameterCount() != argCount)) {
                continue;
            }

            for (int i = 0; i < argCount; ++i) {
                Class<?> argType = method.getParameter(i).getRawType();
                argType.isAssignableFrom(argTypes[i]);
            }

            return method;
        }
        return null;
    }
}
