package io.goodforgod.jackson.module.datetime.configuration.serializer;

import com.fasterxml.jackson.datatype.jsr310.ser.OffsetTimeSerializer;
import java.time.format.DateTimeFormatter;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 11.01.2022
 */
public class OffsetTimeISOSerializer extends OffsetTimeSerializer {

    public OffsetTimeISOSerializer(DateTimeFormatter formatter) {
        super(OffsetTimeSerializer.INSTANCE, null, formatter);
    }
}
