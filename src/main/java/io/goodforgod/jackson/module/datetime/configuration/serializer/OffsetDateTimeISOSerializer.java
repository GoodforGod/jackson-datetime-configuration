package io.goodforgod.jackson.module.datetime.configuration.serializer;

import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import java.time.format.DateTimeFormatter;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 11.01.2022
 */
public class OffsetDateTimeISOSerializer extends OffsetDateTimeSerializer {

    public OffsetDateTimeISOSerializer(DateTimeFormatter formatter) {
        super(OffsetDateTimeSerializer.INSTANCE, null, formatter);
    }
}
