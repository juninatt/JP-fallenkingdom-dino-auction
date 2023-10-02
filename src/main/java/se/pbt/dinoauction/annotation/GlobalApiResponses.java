package se.pbt.dinoauction.annotation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to define global API responses for common HTTP status codes.
 * <p>
 * Use this annotation to decorate API methods where standard HTTP response codes
 * are expected. The annotation defines these standard responses in the OpenAPI specification.
 * </p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success - The request was successful.",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Bad Request - The request could not be understood or was missing required parameters. Please check the request payload and parameters.",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication failed or user does not have permissions. Please ensure you provide valid credentials.",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "403", description = "Forbidden - Access is forbidden to the requested resource. Please ensure you have the necessary permissions.",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "404", description = "Not Found - The requested resource was not found. Please ensure you provide a valid resource identifier.",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "500", description = "Internal Server Error - An unexpected error occurred on the server. Please try again later or contact the support team.",
                content = @Content(mediaType = "application/json"))
})
public @interface GlobalApiResponses {

    /**
     * Specifies the implementation class for the response schema.
     *
     * @return the class that implements the schema for the response, default is Void.
     */
    Class<?> schemaImplementation() default Void.class;

    /**
     * Specifies the media type for the response content.
     *
     * @return the media type for the response content, default is "application/json".
     */
    String mediaType() default "application/json";
}

