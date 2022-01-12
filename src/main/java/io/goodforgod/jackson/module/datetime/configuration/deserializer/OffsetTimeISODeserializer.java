package io.goodforgod.jackson.module.datetime.configuration.deserializer;

import com.fasterxml.jackson.datatype.jsr310.deser.OffsetTimeDeserializer;
import java.time.format.DateTimeFormatter;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 12.01.2022
 */
public class OffsetTimeISODeserializer extends OffsetTimeDeserializer {

    public OffsetTimeISODeserializer(DateTimeFormatter dtf) {
        super(dtf);
    }
}
