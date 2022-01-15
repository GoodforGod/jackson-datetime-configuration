package io.goodforgod.jackson.module.datetime.configuration.serializer;

import static io.goodforgod.jackson.module.datetime.configuration.DateTimeFormatters.*;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.*;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 12.01.2022
 */
public final class Serializers {

    private Serializers() {}

    public static final OffsetDateTimeISOSerializer OFFSET_DATE_TIME = new OffsetDateTimeISOSerializer(ISO_OFFSET_DATE_TIME);
    public static final ZonedDateTimeSerializer ZONED_DATE_TIME = new ZonedDateTimeSerializer(ISO_ZONED_DATE_TIME);

    public static final OffsetTimeISOSerializer OFFSET_TIME = new OffsetTimeISOSerializer(ISO_OFFSET_TIME);
    public static final LocalDateTimeSerializer LOCAL_DATE_TIME = new LocalDateTimeSerializer(ISO_LOCAL_DATE_TIME);
    public static final LocalDateSerializer LOCAL_DATE = new LocalDateSerializer(ISO_LOCAL_DATE);
    public static final LocalTimeSerializer LOCAL_TIME = new LocalTimeSerializer(ISO_LOCAL_TIME);

    public static final YearSerializer YEAR = new YearSerializer(ISO_YEAR);
    public static final YearMonthSerializer YEAR_MONTH = new YearMonthSerializer(ISO_YEAR_MONTH);
    public static final MonthDaySerializer MONTH_DAY = new MonthDaySerializer(ISO_MONTH_DAY);

    public static final ToStringSerializer PERIOD = new ToStringSerializer(Period.class);
    public static final ZoneIdSerializer ZONE_ID = new ZoneIdSerializer();
    public static final ToStringSerializer ZONE_OFFSET = new ToStringSerializer(ZoneOffset.class);

    public static final OffsetTimeISOSerializer JAVA_ISO_OFFSET_TIME = new OffsetTimeISOSerializer(
            DateTimeFormatter.ISO_OFFSET_TIME);
    public static final ZonedDateTimeSerializer JAVA_ISO_ZONED_DATE_TIME = new ZonedDateTimeSerializer(
            DateTimeFormatter.ISO_ZONED_DATE_TIME);
}
