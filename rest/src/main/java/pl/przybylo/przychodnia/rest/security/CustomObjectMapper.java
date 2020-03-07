package pl.przybylo.przychodnia.rest.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static java.time.format.DateTimeFormatter.ofPattern;

@Configuration
public class CustomObjectMapper {

    private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Value("${spring.jackson.serialization.indent-output}")
    private boolean indentOutput;

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, indentOutput);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        objectMapper.setDateFormat(new SimpleDateFormat(LONG_DATE_FORMAT));

        objectMapper.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
        objectMapper.setDateFormat(new SimpleDateFormat(LONG_DATE_FORMAT));

        return objectMapper;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return builder -> {
            builder.serializers(new LocalDateSerializer(ofPattern(SHORT_DATE_FORMAT)));
            builder.serializers(new LocalDateTimeSerializer(ofPattern(LONG_DATE_FORMAT)));
            builder.deserializers(new LocalDateDeserializer(ofPattern(SHORT_DATE_FORMAT)));
            builder.deserializers(new LocalDateTimeDeserializer(ofPattern(LONG_DATE_FORMAT)));
        };
    }

}
