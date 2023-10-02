package se.pbt.dinoauction.model.entity.contact;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.pbt.dinoauction.model.entity.user.Participant;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email is required")
    @Size(max = 256, message = "Email address is to long")
    private String secureEmail;

    @NotBlank(message = "Phone number is required")
    @Size(max = 15, message = "Phone number must follow the  international phone numbering plan (ITU-T E. 164)")
    private String phoneNumber;


    /**
     * Represents the relationship to the associated {@link Participant}.
     * This entity is not the owner; the foreign key 'contact_id' is managed by the Participant entity.
     */
    @OneToOne(mappedBy = "contact")
    private Participant participant;


    /**
     * No-args constructor for JPA operations.
     */
    public Contact() {}

    public Contact(String secureEmail, String phoneNumber, Participant participant) {
        this.secureEmail = secureEmail;
        this.phoneNumber = phoneNumber;
        this.participant = participant;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getSecureEmail() {
        return secureEmail;
    }

    public void setSecureEmail(String secureEmail) {
        this.secureEmail = secureEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Participant getParticipant() {
        return participant;
    }

}

