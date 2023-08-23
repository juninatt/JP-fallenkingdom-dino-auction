package se.pbt.mrcoffee.dto.response;

/**
 * Represents a response structure for a coffee product.
 * <p>
 * This record encapsulates the essential details of a coffee product, omitting any unnecessary or sensitive
 * information, to be used in the response sent to the client.
 */
public record CoffeeResponseDTO(
        long id,
        String name,
        String description,
        String origin,
        String roastLevel,
        String flavorNotes,
        String caffeineContent
) {}
