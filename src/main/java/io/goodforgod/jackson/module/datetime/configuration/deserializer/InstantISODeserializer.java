package io.goodforgod.jackson.module.datetime.configuration.deserializer;

import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 11.01.2022
 */
public class InstantISODeserializer<T extends Temporal> extends InstantDeserializer<T> {

    public InstantISODeserializer(Class<T> supportedType,
                                  DateTimeFormatter formatter,
                                  Function<TemporalAccessor, T> parsedToValue,
                                  Function<FromIntegerArguments, T> fromMilliseconds,
                                  Function<FromDecimalArguments, T> fromNanoseconds,
                                  BiFunction<T, ZoneId, T> adjust,
                                  boolean replaceZeroOffsetAsZ) {
        super(supportedType, formatter, parsedToValue, fromMilliseconds, fromNanoseconds, adjust, replaceZeroOffsetAsZ);
    }

    public InstantISODeserializer(InstantDeserializer<T> base,
                                  DateTimeFormatter f) {
        super(base, f);
    }


}
