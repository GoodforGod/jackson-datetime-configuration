package io.goodforgod.jackson.module.datetime.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 26.04.2021
 */
class InstantDeserializerTests extends Assertions {

    static class User {

        private String name;
        private Instant value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Instant getValue() {
            return value;
        }

        public void setValue(Instant value) {
            this.value = value;
        }
    }

    private static final Instant TIME = OffsetDateTime.ofInstant(Instant.EPOCH, ZoneId.of("UTC")).toInstant();
    private static final String VALUE = "1970-01-01T00:00:00Z";

    private final ObjectMapper mapper = new ObjectMapper().registerModule(JavaTimeModuleConfiguration.ofISO().getModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

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
