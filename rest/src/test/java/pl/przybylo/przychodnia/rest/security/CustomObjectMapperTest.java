package pl.przybylo.przychodnia.rest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class CustomObjectMapperTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public static void setup() {
        objectMapper = new CustomObjectMapper().objectMapper();
    }

    @Test
    public void should_properly_serialize_dateTime_value() throws IOException {
        // given
        LocalDateTime date = LocalDateTime.of(2020, 3, 6, 9, 20, 0);

        // when
        String result = objectMapper.writeValueAsString(date);

        // then
        assertEquals("\"2020-03-06T09:20:00\"", result);
    }

    @Test
    public void should_properly_deserialize_dateTime_value() throws IOException {
        // given
        String date = "\"2020-03-06T09:20:00\"";

        // when
        LocalDateTime result = objectMapper.readValue(date, LocalDateTime.class);

        // then
        assertEquals(LocalDateTime.of(2020, 3, 6, 9, 20, 0), result);
    }

}
