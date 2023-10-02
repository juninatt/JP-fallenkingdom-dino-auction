package se.pbt.dinoauction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.pbt.dinoauction.model.contact.Contact;
import se.pbt.dinoauction.model.user.Participant;

/**
 * A ContactDTO for the {@link Contact} class used  for transferring contact information between different parts of the application.
 * It holds a secure email and phone number for a contact, along with a reference to the Participant object associated with this contact.
 */
public record ContactDTO(

        @NotBlank(message = "Email is required")
        @Size(max = 256, message = "Email address is too long")
        String secureEmail,

        @NotBlank(message = "Phone number is required")
        @Size(max = 15, message = "Phone number is too long")
        String phoneNumber,

        Participant participant
) {}
