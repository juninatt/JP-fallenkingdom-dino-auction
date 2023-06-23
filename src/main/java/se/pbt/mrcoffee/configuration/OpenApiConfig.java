package se.pbt.mrcoffee.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI documentation. See {@link <a href="http://localhost:8080/swagger-ui/index.html">https://example.com</a>}
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Mr Coffee API",
                version = "SNAPSHOT",
                description = "API for managing coffee products including suppliers, customers, and payment transactions",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Mr Coffee Team",
                        email = "petter.bergstrom@protonmail.com"
                )
        )
)
public class OpenApiConfig {

    /**
     * GroupedOpenApi bean for the address-related API endpoints.
     *
     * @return GroupedOpenApi instance for the address API.
     */
    @Bean
    public GroupedOpenApi addressApi() {
        return GroupedOpenApi.builder()
                .group("address")
                .packagesToScan("se.pbt.mrcoffee.controller.address")
                .build();
    }

    /**
     * GroupedOpenApi bean for the contact-related API endpoints.
     *
     * @return GroupedOpenApi instance for the contact API.
     */
    @Bean
    public GroupedOpenApi contactApi() {
        return GroupedOpenApi.builder()
                .group("contact")
                .packagesToScan("se.pbt.mrcoffee.controller.contact")
                .build();
    }

    /**
     * GroupedOpenApi bean for the product-related API endpoints.
     *
     * @return GroupedOpenApi instance for the product API.
     */
    @Bean
    public GroupedOpenApi productApi() {
        return GroupedOpenApi.builder()
                .group("product")
                .packagesToScan("se.pbt.mrcoffee.controller.product")
                .build();
    }

    /**
     * GroupedOpenApi bean for the receipt-related API endpoints.
     *
     * @return GroupedOpenApi instance for the receipt API.
     */
    @Bean
    public GroupedOpenApi receiptApi() {
        return GroupedOpenApi.builder()
                .group("receipt")
                .packagesToScan("se.pbt.mrcoffee.controller.receipt")
                .build();
    }
}
