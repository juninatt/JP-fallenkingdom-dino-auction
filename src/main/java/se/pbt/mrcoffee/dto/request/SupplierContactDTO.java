package se.pbt.mrcoffee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.contact.SupplierContact;

/**
 * Data Transfer Object for transferring {@link SupplierContact} data.
 * <p>
 * This DTO includes the essential fields related to the contact information
 * of a supplier, and it's used for transferring data across different layers
 * or systems.
 */
public record SupplierContactDTO(

        @NotBlank(message = "Email is required")
        @Size(max = 256, message = "Email address is too long")
        String email,

        @NotBlank(message = "Phone number is required")
        @Size(max = 15, message = "Phone number must follow the international phone numbering plan (ITU-T E. 164)")
        String phoneNumber,

        @Size(max = 255, message = "Additional information can't have more than 255 characters")
        String additionalInfo,

        @NotBlank(message = "Company name is required")
        @Size(max = 30, message = "Company name can't be more than 30 characters")
        String companyName,

        @NotBlank(message = "Industry is required")
        @Size(max = 30, message = "Industry can't be more than 30 characters")
        String industry
) {}
