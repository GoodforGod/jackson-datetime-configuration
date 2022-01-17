package io.goodforgod.jackson.module.datetime.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.MonthDay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 27.04.2021
 */
class MonthDayCustomDeserializerTests extends Assertions {

    static class User {

        private String name;
        private MonthDay value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public MonthDay getValue() {
            return value;
        }

        public void setValue(MonthDay value) {
            this.value = value;
        }
    }

    private static final MonthDay TIME = MonthDay.of(1, 1);
    private static final String VALUE = "01:01";

    private final ObjectMapper adapter = new ObjectMapper().registerModule(JavaTimeModuleConfiguration.ofISO()
            .setMonthDayFormat("MM:dd")
            .getModule());

    @Test
    void serializationIsValid() throws JsonProcessingException {
        final User user = new User();
        user.setName("Bob");
        user.setValue(TIME);

        final String json = adapter.writeValueAsString(user);
        assertNotNull(json);
        assertTrue(json.contains("\"value\":\"" + VALUE + "\""), json);
    }

    @Test
    void deserializationIsValid() throws JsonProcessingException {
        final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE + "\"}";

        final User user = adapter.readValue(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(TIME, user.getValue());
    }

    @Test
    void deserializationIsValidForStringNumber() throws JsonProcessingException {
        final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE + "\"}";

        final User user = adapter.readValue(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(TIME, user.getValue());
    }

    @Test
    void deserializationFails() {
        final String json = "{\"name\":\"Bob\",\"value\":\"NOT_TIME\"}";

        try {
            adapter.readValue(json, User.class);
            fail("Should not happen");
        } catch (JsonProcessingException e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void deserializationFailsForArray() throws JsonProcessingException {
        final String json = "{\"name\":\"Bob\",\"value\":[\"NOT_TIME\"]}";

        try {
            adapter.readValue(json, User.class);
            fail("Should not happen");
        } catch (JsonProcessingException e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }
}
