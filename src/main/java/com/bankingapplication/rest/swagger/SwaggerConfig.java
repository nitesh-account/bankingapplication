package com.bankingapplication.rest.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Kumar, Nitesh
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public Docket BankingApplicationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bankingapplication.rest.controller"))
                .build()
                .apiInfo(apiInfo()).pathMapping("")
                .globalOperationParameters(
                        Stream.of(new ParameterBuilder()
                                .name("x-username")
                                .description("Created by")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build()).collect(Collectors.toList()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Banking application API",
                "Banking application API's used for creating, inquire, update and delete operations for customer and account. Also, provides deposit and withdrawal operations.",
                "1.0",
                "Terms of service",
                new Contact("Nitesh Kumar", "https://engineeernitesh.blogspot.com/", "nitesh04singh@gmail.com"),
                "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }
}
