package io.daff.notes.config;

import io.daff.notes.handler.ApiVersionHandlerMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer, WebMvcRegistrations {

    // @Resource
    // private LogInterceptor logInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(logInterceptor)
        //         .addPathPatterns("/**")
        //         .excludePathPatterns("/login");
    }

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiVersionHandlerMapping();
    }
}
