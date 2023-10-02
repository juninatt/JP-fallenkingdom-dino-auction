package se.pbt.dinoauction.model.transaction;

import jakarta.persistence.*;
import se.pbt.dinoauction.model.auctionitem.AuctionItem;
import se.pbt.dinoauction.model.user.Participant;

import java.util.List;

/**
 * Represents an auction transaction, capturing all the related data and associations such as
 * the {@link Participant}, {@link AuctionItem}, {@link Payment}, and {@link DropCoordinates}.
 */
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean completed;


    /**
     * Represents the relationship to the {@link Participant} associated with this Transaction.
     * This entity is not the owner; the foreign key 'participant_id' is managed by the Participant entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Participant participant;

    /**
     * Represents the relationship to the {@link AuctionItem} list associated with this Transaction.
     * Transaction is the owning entity, holding the foreign key 'transaction_id' in the AuctionItem table.
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_id")
    private List<AuctionItem> auctionItems;

    /**
     * Represents the relationship to {@link Payment} associated with this Transaction.
     * Transaction is the owning entity, managing the foreign key 'transaction_id' in the Payment table.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    /**
     * Represents the relationship to the {@link DropCoordinates} associated with this transaction.
     * Transaction is the owning entity, holding the foreign key 'drop_coordinates_id' in its table.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drop_coordinates_id")
    private DropCoordinates dropCoordinates;


    /**
     * No-args constructor for JPA operations.
     */
    public Transaction() {
    }

    public Transaction(Participant participant, Payment payment) {
        this.participant = participant;
        this.payment = payment;
        this.completed = false;
    }

    // Getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<AuctionItem> getAuctionItems() {
        return auctionItems;
    }

    public void setAuctionItems(List<AuctionItem> auctionItems) {
        this.auctionItems = auctionItems;
    }

    public DropCoordinates getDropCoordinates() {
        return dropCoordinates;
    }

    public void setDropCoordinates(DropCoordinates dropCoordinates) {
        this.dropCoordinates = dropCoordinates;
    }
}
