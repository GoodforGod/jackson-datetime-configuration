package io.goodforgod.jackson.module.datetime.configuration.serializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 11.01.2022
 */
public class InstantISOSerializer extends InstantSerializer {

    public InstantISOSerializer(InstantSerializer base,
                                Boolean useTimestamp,
                                DateTimeFormatter formatter) {
        super(base, useTimestamp, formatter);
    }
}
