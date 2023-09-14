package se.pbt.mrcoffee.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Manages OpenAPI documentation settings.
 * <p>
 * The API documentation can be accessed at
 * {@link <a href="http://localhost:8080/swagger-ui/index.html">Swagger UI</a>}.
 * </p>
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
     * Configures API documentation for user-related endpoints.
     *
     * @return A {@link GroupedOpenApi} object containing user API configurations.
     */
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .packagesToScan("se.pbt.mrcoffee.controller.user")
                .build();
    }

    /**
     * Configures API documentation for order-related endpoints.
     *
     * @return A {@link GroupedOpenApi} object containing order API configurations.
     */
    @Bean
    public GroupedOpenApi addressApi() {
        return GroupedOpenApi.builder()
                .group("address")
                .packagesToScan("se.pbt.mrcoffee.controller.address")
                .build();
    }

    /**
     * Configures API documentation for contact-related endpoints.
     *
     * @return A {@link GroupedOpenApi} object containing contact API configurations.
     */
    @Bean
    public GroupedOpenApi contactApi() {
        return GroupedOpenApi.builder()
                .group("contact")
                .packagesToScan("se.pbt.mrcoffee.controller.contact")
                .build();
    }

    /**
     * Configures API documentation for product-related endpoints.
     *
     * @return A {@link GroupedOpenApi} object containing product API configurations.
     */
    @Bean
    public GroupedOpenApi productApi() {
        return GroupedOpenApi.builder()
                .group("product")
                .packagesToScan("se.pbt.mrcoffee.controller.product")
                .build();
    }
}
