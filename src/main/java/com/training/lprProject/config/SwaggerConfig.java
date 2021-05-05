package com.training.lprProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.training.lprProject.controller"))
                .paths(PathSelectors.ant("/management/api/v1/students/**"))
                .build()
                .apiInfo(apiDetails())
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(new BasicAuth("basicAuth")));
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "User Management API",
                "Simple API for CRUD operations with students/admins",
                "1.0",
                "Free to use",
                new Contact("Sinyavskiy Kirill", "https://mySite.com", "javadevsinyavskiy@gmail.com"),
                "API License",
                "https://mySite.com",
                Collections.emptyList()
        );
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("basicAuth", new AuthorizationScope[0])))
                .build();
    }
}
