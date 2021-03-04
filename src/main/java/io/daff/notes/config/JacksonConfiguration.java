package io.daff.notes.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.daff.notes.util.JacksonUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Json配置
 *
 * @author daffupman
 * @since 2020/7/12
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return JacksonUtil.getInstance();
    }

}
