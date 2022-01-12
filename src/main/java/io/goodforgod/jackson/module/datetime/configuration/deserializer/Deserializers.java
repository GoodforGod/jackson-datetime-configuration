package io.goodforgod.jackson.module.datetime.configuration.deserializer;

import com.fasterxml.jackson.datatype.jsr310.deser.*;
import io.goodforgod.jackson.module.datetime.configuration.DateTimeFormatters;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 12.01.2022
 */
public final class Deserializers {

    public static final InstantISODeserializer<Instant> INSTANT = new InstantISODeserializer<>(
            Instant.class,
            DateTimeFormatters.ISO_INSTANT,
            Instant::from,
            a -> Instant.ofEpochMilli(a.value),
            a -> Instant.ofEpochSecond(a.integer, a.fraction),
            null,
            true);

    public static final InstantISODeserializer<OffsetDateTime> OFFSET_DATE_TIME = new InstantISODeserializer<>(
            OffsetDateTime.class,
            DateTimeFormatters.ISO_OFFSET_DATE_TIME,
            OffsetDateTime::from,
            a -> OffsetDateTime.ofInstant(Instant.ofEpochMilli(a.value), a.zoneId),
            a -> OffsetDateTime.ofInstant(Instant.ofEpochSecond(a.integer, a.fraction), a.zoneId),
            (d, z) -> (d.isEqual(OffsetDateTime.MIN) || d.isEqual(OffsetDateTime.MAX)
                    ? d
                    : d.withOffsetSameInstant(z.getRules().getOffset(d.toLocalDateTime()))),
            true);

    public static final InstantISODeserializer<ZonedDateTime> ZONED_DATE_TIME = new InstantISODeserializer<>(
            ZonedDateTime.class,
            DateTimeFormatters.ISO_ZONED_DATE_TIME,
            ZonedDateTime::from,
            a -> ZonedDateTime.ofInstant(Instant.ofEpochMilli(a.value), a.zoneId),
            a -> ZonedDateTime.ofInstant(Instant.ofEpochSecond(a.integer, a.fraction), a.zoneId),
            ZonedDateTime::withZoneSameInstant,
            true);

    public static final OffsetTimeDeserializer OFFSET_TIME = new OffsetTimeISODeserializer(DateTimeFormatters.ISO_OFFSET_TIME);
    public static final LocalDateTimeDeserializer LOCAL_DATE_TIME = new LocalDateTimeDeserializer(
            DateTimeFormatters.ISO_LOCAL_DATE_TIME);
    public static final LocalDateDeserializer LOCAL_DATE = new LocalDateDeserializer(DateTimeFormatters.ISO_LOCAL_DATE);
    public static final LocalTimeDeserializer LOCAL_TIME = new LocalTimeDeserializer(DateTimeFormatters.ISO_LOCAL_TIME);

    public static final YearDeserializer YEAR = new YearDeserializer(DateTimeFormatters.ISO_YEAR);
    public static final YearMonthDeserializer YEAR_MONTH = new YearMonthDeserializer(DateTimeFormatters.ISO_YEAR_MONTH);
    public static final MonthDayDeserializer MONTH_DAY = new MonthDayDeserializer(DateTimeFormatters.ISO_MONTH_DAY);

    private Deserializers() {}
}
