package se.pbt.dinoauction.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Manages OpenAPI documentation settings.
 * <p>
 * When application is running API documentation can be accessed at
 * {@link <a href="http://localhost:8080/swagger-ui/index.html">Swagger UI</a>}.
 * </p>
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Dinosaur Auction API",
                version = "0.1",
                description = "The Dinosaur API enables CRUD operations for a dinosaur auction platform(see Jurassic World: Fallen Kingdom)," +
                        "allowing users to manage dinosaur species, gender, weight, and price, as well as participate in bidding and auction events.",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Organizer:",
                        email = "petter.bergstrom@protonmail.com"
                )
        )
)
public class OpenApiConfig {

    private static final String DINO_PACKAGE = "se.pbt.dinoauction.controller";
    private static final String DINO_PATH = "/dinosaurs/**";
    private static final String TRANSACTIONS_PATH = "/transactions/**";
    private static final String CONTACTS_PATH = "/contacts/**";
    private static final String USERS_PATH = "/users/**";

    @Bean
    public GroupedOpenApi dinosaursApi() {
        return GroupedOpenApi.builder()
                .group("dinosaurs")
                .pathsToMatch(DINO_PATH)
                .packagesToScan(DINO_PACKAGE)
                .build();
    }

    @Bean
    public GroupedOpenApi transactionsApi() {
        return GroupedOpenApi.builder()
                .group("transactions")
                .pathsToMatch(TRANSACTIONS_PATH)
                .packagesToScan(DINO_PACKAGE)
                .build();
    }

    @Bean
    public GroupedOpenApi contactsApi() {
        return GroupedOpenApi.builder()
                .group("contacts")
                .pathsToMatch(CONTACTS_PATH)
                .packagesToScan(DINO_PACKAGE)
                .build();
    }

    @Bean
    public GroupedOpenApi usersApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch(USERS_PATH)
                .packagesToScan(DINO_PACKAGE)
                .build();
    }
}
