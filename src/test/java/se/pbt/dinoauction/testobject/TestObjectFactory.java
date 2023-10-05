package se.pbt.dinoauction.testobject;

import se.pbt.dinoauction.model.dto.ContactDTO;
import se.pbt.dinoauction.model.dto.DinoCardDataDTO;
import se.pbt.dinoauction.model.dto.DinosaurDTO;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;
import se.pbt.dinoauction.model.entity.contact.Contact;
import se.pbt.dinoauction.model.entity.transaction.DropCoordinates;
import se.pbt.dinoauction.model.entity.transaction.Payment;
import se.pbt.dinoauction.model.entity.transaction.Transaction;
import se.pbt.dinoauction.model.entity.user.Participant;

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
                "Non-binary",
                2,
                12,
                "Cute",
                BigDecimal.ONE
        );
    }

    /**
     * @return A {@link Dinosaur} object with pre-set field values
     */
    public static DinosaurDTO dinosaurDTO() {
        return new DinosaurDTO(
                "SkyScream",
                "Pteranodon",
                "Female",
                2,
                15,
                "Shimmering emerald feathers and an impressive wingspan of over 25 feet.",
                BigDecimal.valueOf(15_000_000_000L),
                null
        );
    }

    /**
     * @return A {@link DinoCardDataDTO} object with pre-set field values.
     */
    public static DinoCardDataDTO dinoCardDataDTO() {
        return new DinoCardDataDTO(
                "CardDinoName",
                "CardSpecies",
                "CardGender",
                3.0,
                15,
                "CardDescription",
                BigDecimal.TEN,
                "CardImageResourceURL"
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
