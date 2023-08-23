package se.pbt.mrcoffee.dto.response;

import se.pbt.mrcoffee.model.contact.SupplierContact;

/**
 * A record representing the response structure for a {@link SupplierContact}.
 * <p>
 * This record is used to encapsulate the contact information of a supplier in the system, providing
 * a means to selectively send required details in the response.
 * <p>
 * It includes essential attributes such as email, phone number, company name, and industry.
 */
public record SupplierContactResponseDTO(
        long id,
        String email,
        String phoneNumber,
        String additionalInfo,
        String companyName,
        String industry
) {}

