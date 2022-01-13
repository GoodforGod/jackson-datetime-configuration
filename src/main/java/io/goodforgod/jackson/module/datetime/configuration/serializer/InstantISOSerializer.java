package io.goodforgod.jackson.module.datetime.configuration.serializer;

import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import java.time.format.DateTimeFormatter;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 11.01.2022
 */
public class InstantISOSerializer extends InstantSerializer {

    public InstantISOSerializer(DateTimeFormatter formatter) {
        super(InstantSerializer.INSTANCE, null, formatter);
    }
}
