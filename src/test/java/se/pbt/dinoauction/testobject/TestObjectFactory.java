package se.pbt.dinoauction.testobject;

import se.pbt.dinoauction.dto.ContactDTO;
import se.pbt.dinoauction.dto.DinosaurDTO;
import se.pbt.dinoauction.model.auctionitem.Dinosaur;
import se.pbt.dinoauction.model.contact.Contact;
import se.pbt.dinoauction.model.transaction.DropCoordinates;
import se.pbt.dinoauction.model.transaction.Payment;
import se.pbt.dinoauction.model.transaction.Transaction;
import se.pbt.dinoauction.model.user.Participant;

import java.math.BigDecimal;

/**
 * Factory class to create test objects.
 */
public class TestObjectFactory {

    // Auction Items

    /**
     * @return A {@link Dinosaur} object with pre-set field values
     */
    public static Dinosaur dinosaur() {
        return new Dinosaur(
                "DinoName",
                "Species",
                BigDecimal.ONE,
                "Species",
                "Gender",
                12
        );
    }

    /**
     * @return A {@link Dinosaur} object with pre-set field values
     */
    public static DinosaurDTO dinosaurDTO() {
        return new DinosaurDTO(
                "SkyScream",
                "Pteranodon",
                "Shimmering emerald feathers and an impressive wingspan of over 25 feet.",
                "Female",
                15000,
                BigDecimal.valueOf(15_000_000_000L)
        );
    }

    // Participants

    /**
     * @return A Russia based secret {@link Participant}
     */
    public static Participant participant() {
        return new Participant("RedCzar","BolshevikBling!44", "RetroRed");
    }


    // DropCoordinates

    /**
     * @return A {@link DropCoordinates} pointing to Area 51
     */
    public static DropCoordinates dropCoordinates() {
        return new DropCoordinates(37.2350, -115.8111
        );
    }


    // Contact objects

    /**
     * @return A {@link Contact} object with pre-set field values
     */
    public static Contact contact() {
        return new Contact("email", "031", null);
    }

    /**
     * @return A {@link ContactDTO} object with pre-set field values
     */
    public static ContactDTO contactDTO() {
        return new ContactDTO(
                "email@example.com",
                "123",
                null
        );
    }


    // Transaction objects

    /**
     * @return A {@link Transaction} object with pre-set field values
     */
    public static Transaction transaction() {
        return new Transaction(
                participant(),
                createPayment()
        );
    }

    // Payment objects

    /**
     * @return A {@link Payment} object with pre-set field values
     */
    private static Payment createPayment() {
        return new Payment(
                "Pending",
                "Cash",
                null
        );
    }
}
