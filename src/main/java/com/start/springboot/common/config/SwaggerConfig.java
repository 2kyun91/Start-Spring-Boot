package com.start.springboot.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Start-Spring-Boot-RestAPI-Swagger2")
                .version("0.0.1")
                .description("Start Spring Boot 블로그 프로젝트의 API 명세");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}

