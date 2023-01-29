package com.muravev.monitoringservice.configs;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.List;

@Configuration
public class WebConfiguration implements WebFluxConfigurer {
    @Value("${front.urls}")
    private List<String> frontUrls;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(frontUrls.toArray(new String[0]))
                .allowedMethods("PUT", "OPTIONS", "GET", "POST", "PATCH", "DELETE")
                .allowCredentials(true);
    }
}
