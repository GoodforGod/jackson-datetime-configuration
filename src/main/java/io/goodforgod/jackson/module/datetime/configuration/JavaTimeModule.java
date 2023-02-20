package io.goodforgod.jackson.module.datetime.configuration;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
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

    public JavaTimeModule(JavaTimeModuleConfiguration configuration) {
        super(PackageVersion.VERSION);

        // First deserializers
        final InstantDeserializer<Instant> instantDeserializer = getInstantDeserializer(configuration.getInstantFormat());
        final OffsetTimeDeserializer offsetTimeDeserializer = getOffsetTimeDeserializer(configuration.getOffsetTimeFormat());
        final InstantDeserializer<OffsetDateTime> offsetDateTimeDeserializer = getOffsetDateTimeDeserializer(
                configuration.getOffsetTimeFormat());
        final InstantDeserializer<ZonedDateTime> zonedDateTimeDeserializer = getZonedDateTimeDeserializer(
                configuration.getZonedDateTimeFormat());
        final LocalDateTimeDeserializer localDateTimeDeserializer = getLocalDateTimeDeserializer(
                configuration.getLocalDateTimeFormat());
        final LocalDateDeserializer localDateDeserializer = getLocalDateDeserializer(configuration.getLocalDateFormat());
        final LocalTimeDeserializer localTimeDeserializer = getLocalTimeDeserializer(configuration.getLocalTimeFormat());
        final YearDeserializer yearDeserializer = getYearDeserializer(configuration.getYearFormat());
        final YearMonthDeserializer yearMonthDeserializer = getYearMonthDeserializer(configuration.getYearMonthFormat());
        final MonthDayDeserializer monthDayDeserializer = getMonthDayDeserializer(configuration.getMonthDayFormat());

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
        final InstantSerializer instantSerializer = getInstantSerializer(configuration.getInstantFormat());
        final OffsetTimeSerializer offsetTimeSerializer = getOffsetTimeSerializer(configuration.getOffsetTimeFormat());
        final OffsetDateTimeSerializer offsetDateTimeSerializer = getOffsetDateTimeSerializer(
                configuration.getOffsetDateTimeFormat());
        final ZonedDateTimeSerializer zonedDateTimeSerializer = getZonedDateTimeSerializer(
                configuration.getZonedDateTimeFormat());
        final LocalDateTimeSerializer localDateTimeSerializer = getLocalDateTimeSerializer(
                configuration.getLocalDateTimeFormat());
        final LocalDateSerializer localDateSerializer = getLocalDateSerializer(configuration.getLocalDateFormat());
        final LocalTimeSerializer localTimeSerializer = getLocalTimeSerializer(configuration.getLocalTimeFormat());
        final YearSerializer yearSerializer = getYearSerializer(configuration.getYearFormat());
        final YearMonthSerializer yearMonthSerializer = getYearMonthSerializer(configuration.getYearMonthFormat());
        final MonthDaySerializer monthDaySerializer = getMonthDaySerializer(configuration.getMonthDayFormat());

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
        if (formatter == null) {
            return Deserializers.INSTANT;
        } else {
            return new InstantISODeserializer<>(InstantDeserializer.INSTANT, formatter);
        }
    }

    protected InstantDeserializer<OffsetDateTime> getOffsetDateTimeDeserializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Deserializers.OFFSET_DATE_TIME;
        } else {
            return new InstantISODeserializer<>(Deserializers.OFFSET_DATE_TIME, formatter);
        }
    }

    protected InstantDeserializer<ZonedDateTime> getZonedDateTimeDeserializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Deserializers.ZONED_DATE_TIME;
        } else {
            return new InstantISODeserializer<>(Deserializers.ZONED_DATE_TIME, formatter);
        }
    }

    protected OffsetTimeDeserializer getOffsetTimeDeserializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Deserializers.OFFSET_TIME;
        } else {
            return new OffsetTimeISODeserializer(formatter);
        }
    }

    protected LocalDateTimeDeserializer getLocalDateTimeDeserializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Deserializers.LOCAL_DATE_TIME;
        } else {
            return new LocalDateTimeDeserializer(formatter);
        }
    }

    protected LocalDateDeserializer getLocalDateDeserializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Deserializers.LOCAL_DATE;
        } else {
            return new LocalDateDeserializer(formatter);
        }
    }

    protected LocalTimeDeserializer getLocalTimeDeserializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Deserializers.LOCAL_TIME;
        } else {
            return new LocalTimeDeserializer(formatter);
        }
    }

    protected YearDeserializer getYearDeserializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Deserializers.YEAR;
        } else {
            return new YearDeserializer(formatter);
        }
    }

    protected YearMonthDeserializer getYearMonthDeserializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Deserializers.YEAR_MONTH;
        } else {
            return new YearMonthDeserializer(formatter);
        }
    }

    protected MonthDayDeserializer getMonthDayDeserializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Deserializers.MONTH_DAY;
        } else {
            return new MonthDayDeserializer(formatter);
        }
    }

    protected InstantSerializer getInstantSerializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Serializers.INSTANT;
        } else {
            return new InstantISOSerializer(formatter);
        }
    }

    protected OffsetDateTimeSerializer getOffsetDateTimeSerializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Serializers.OFFSET_DATE_TIME;
        } else {
            return new OffsetDateTimeISOSerializer(formatter);
        }
    }

    protected ZonedDateTimeSerializer getZonedDateTimeSerializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Serializers.ZONED_DATE_TIME;
        } else {
            return new ZonedDateTimeSerializer(formatter);
        }
    }

    protected OffsetTimeSerializer getOffsetTimeSerializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Serializers.OFFSET_TIME;
        } else {
            return new OffsetTimeISOSerializer(formatter);
        }
    }

    protected LocalDateTimeSerializer getLocalDateTimeSerializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Serializers.LOCAL_DATE_TIME;
        } else {
            return new LocalDateTimeSerializer(formatter);
        }
    }

    protected LocalDateSerializer getLocalDateSerializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Serializers.LOCAL_DATE;
        } else {
            return new LocalDateSerializer(formatter);
        }
    }

    protected LocalTimeSerializer getLocalTimeSerializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Serializers.LOCAL_TIME;
        } else {
            return new LocalTimeSerializer(formatter);
        }
    }

    protected YearSerializer getYearSerializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Serializers.YEAR;
        } else {
            return new YearSerializer(formatter);
        }
    }

    protected YearMonthSerializer getYearMonthSerializer(DateTimeFormatter formatter) {
        if (formatter == null) {
            return Serializers.YEAR_MONTH;
        } else {
            return new YearMonthSerializer(formatter);
        }
    }

    protected MonthDaySerializer getMonthDaySerializer(DateTimeFormatter formatter) {
        if (formatter == null) {
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
                return defaultInstantiator;
            }
        });
    }
}
