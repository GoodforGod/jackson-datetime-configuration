package io.goodforgod.jackson.module.datetime.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.YearMonth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Anton Kurako (GoodforGod)
 * @since 27.04.2021
 */
class YearMonthDeserializerTests extends Assertions {

    static class User {

        private String name;
        private YearMonth value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public YearMonth getValue() {
            return value;
        }

        public void setValue(YearMonth value) {
            this.value = value;
        }
    }

    private static final YearMonth TIME = YearMonth.of(2000, 1);
    private static final String VALUE = "2000-01";

    private final ObjectMapper mapper = new ObjectMapper().registerModule(JavaTimeModuleConfiguration.ofISO().getModule());

    @Test
    void serializationIsValid() throws JsonProcessingException {
        final User user = new User();
        user.setName("Bob");
        user.setValue(TIME);

        final String json = mapper.writeValueAsString(user);
        assertNotNull(json);
        assertTrue(json.contains("\"value\":\"" + VALUE + "\""), json);
    }

    @Test
    void deserializationFromStringIsValid() throws JsonProcessingException {
        final String json = "{\"name\":\"Bob\",\"value\":\"" + VALUE + "\"}";

        final User user = mapper.readValue(json, User.class);
        assertNotNull(user);
        assertEquals("Bob", user.getName());
        assertEquals(TIME, user.getValue());
    }

    @Test
    void deserializationFails() throws JsonProcessingException {
        final String json = "{\"name\":\"Bob\",\"value\":\"NOT_TIME\"}";

        try {
            mapper.readValue(json, User.class);
            fail("Should not happen");
        } catch (JsonProcessingException e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }
}
