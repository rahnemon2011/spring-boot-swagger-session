package com.hadi.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger Config
 * to access Swagger UI , write http://localhost:8080/swagger-ui.html url.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("full-student-api")
                .apiInfo(metaData())
                .select()
                .paths(studentPaths())
                .build();
//                .securitySchemes(newArrayList(oauth()))
//                .securityContexts(newArrayList(securityContext()));
    }

    private Predicate<String> studentPaths() {
        return regex("/students.*");
    }

    @Bean
    public Docket courseApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("full-course-api")
                .apiInfo(metaData())
                .select()
                .paths(coursePaths())
                .build();
    }

    private Predicate<String> coursePaths() {
        return or(
                regex("/courses/enrolment.*"),
                regex("/courses/student.*")
        );
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot Sample")
                .description("In this small sample, I tried to use some of practical frameworks and APIs " +
                        "related to spring boot projects. Below you can see these:<br/>" +
                        "<br/>* Spring Boot" +
                        "<br/>* Spring Rest" +
                        "<br/>* Spring Swagger" +
                        "<br/>* Spring Session" +
                        "<br/>* Spring JPA" +
                        "<br/>* Java8 DateTime" +
                        "<br/>* Test(Integration and MocMVC" +
                        "<br/>* Lombok" +
                        "<br/>* spring profile" +
                        "<br/>* MySql"
                )
                .termsOfServiceUrl("http://springfox.io")
                .version("1.0.0")
                .termsOfServiceUrl("Terms of service")
                .contact(new Contact("Hadi", "https://github.com/rahnemon2011", "rahnemon2011@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
