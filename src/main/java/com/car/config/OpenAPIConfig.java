package com.example.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("汽车租赁系统API文档")
                        .description("接口文档")
                        .version("v1"))
                .externalDocs(new ExternalDocumentation()
                        .description("汽车租赁系统API文档")
                        .url("/"));
    }
}
