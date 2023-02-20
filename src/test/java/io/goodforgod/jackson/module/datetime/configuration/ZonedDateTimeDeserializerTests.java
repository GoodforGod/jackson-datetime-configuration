package io.goodforgod.jackson.module.datetime.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.time.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.04.2021
 */
class ZonedDateTimeDeserializerTests extends Assertions {

    static class User {

        private String name;
        private ZonedDateTime value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ZonedDateTime getValue() {
            return value;
        }

        public void setValue(ZonedDateTime value) {
            this.value = value;
        }
    }

    private static final ZonedDateTime TIME = ZonedDateTime.ofInstant(Instant.EPOCH, ZoneId.of("UTC"));
    private static final String VALUE = "1970-01-01T00:00:00.000Z[UTC]";

    private static final ZonedDateTime TIME_NON_UTC = ZonedDateTime
            .ofInstant(LocalDateTime.ofInstant(Instant.EPOCH, ZoneId.of("Europe/Paris")), ZoneOffset.UTC,
                    ZoneId.of("Europe/Paris"));

    private static final String VALUE_NON_UTC = "1970-01-01T02:00:00.000+01:00[Europe/Paris]";

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModuleConfiguration().getModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);

    @Test
    void serializationIsValidForIso() throws JsonProcessingException {
        final User user = new User();
        user.setName("Bob");
        user.setValue(TIME);

        final String json = mapper.writeValueAsString(user);
        assertNotNull(json);
        assertTrue(json.contains("\"value\":\"" + VALUE + "\""), json);
    }

    @Test
    void deserializationIsValidForIso() throws JsonProcessingException {
        final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE + "\"}";

        final User user = mapper.readValue(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(TIME, user.getValue());
    }

    @Test
    void serializationIsValidForIsoNonUtc() throws JsonProcessingException {
        final User user = new User();
        user.setName("Bob");
        user.setValue(TIME_NON_UTC);

        final String json = mapper.writeValueAsString(user);
        assertNotNull(json);
        assertTrue(json.contains("\"value\":\"" + VALUE_NON_UTC + "\""), json);
    }

    @Test
    void deserializationIsValidForIsoNonUtc() throws JsonProcessingException {
        final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE_NON_UTC + "\"}";

        final User user = mapper.readValue(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(TIME_NON_UTC, user.getValue());
    }

    @Test
    void deserializationFails() {
        final String json = "{\"name\":\"Bob\",\"value\":\"1970-01-01 03:00\"}";

        try {
            mapper.readValue(json, User.class);
            fail("Should not happen");
        } catch (JsonProcessingException e) {
            assertTrue(e.getCause() instanceof DateTimeException);
        }
    }
}
