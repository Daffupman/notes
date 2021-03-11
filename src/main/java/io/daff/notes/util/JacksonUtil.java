package io.daff.notes.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Jackson工具类
 *
 * @author daffupman
 * @since 2020/7/13
 */
public class JacksonUtil {

    /**
     * 时间格式
     */
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);

    private static final ObjectMapper objectMapper = generateDefaultObjectMapper();

    private static ObjectMapper generateDefaultObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 日期格式化
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dtf))
                .addSerializer(new LocalDateTimeSerializer(dtf));

        // long类型自动转string类型
        // SimpleModule longToStringModule = new SimpleModule();
        // longToStringModule.addSerializer(Long.class, ToStringSerializer.instance);
        // longToStringModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        return objectMapper.registerModules(javaTimeModule)
                .configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setDateFormat(sdf);
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * 将bean实例转换成Json字符串
     *
     * @param bean bean实例
     * @param <T>  bean实例的类型
     * @return bean实例的json字符串
     */
    public static <T> String fromBean(T bean) throws JsonProcessingException {
        if (bean == null) {
            return null;
        }

        return bean instanceof String ? ((String) bean) : objectMapper.writeValueAsString(bean);
    }

    /**
     * 将json字符串转换成bean对象
     */
    public static <T> T toBean(String json, TypeReference<T> typeReference) throws JsonProcessingException {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        return (T) (typeReference.getType().equals(String.class) ? json : objectMapper.readValue(json, typeReference));
    }

    /**
     * 将json字符串转换成bean对象
     */
    public static <T> T toBean(String json, Class<?> clazz) throws JsonProcessingException {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        return (T) (clazz.equals(String.class) ? json : objectMapper.readValue(json, clazz));
    }
}
