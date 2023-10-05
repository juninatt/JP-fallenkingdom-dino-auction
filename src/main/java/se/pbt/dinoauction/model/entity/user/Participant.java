package se.pbt.dinoauction.model.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import se.pbt.dinoauction.model.entity.contact.Contact;
import se.pbt.dinoauction.model.entity.transaction.Transaction;

import java.util.List;

@Entity
@Table(name = "participants")
@Inheritance(strategy = InheritanceType.JOINED)
public class Participant extends AppUser {

    @Column(unique = true)
    @NotBlank(message = "Alias is required")
    private String alias;


    /**
     * Represents the relationship to the associated {@link Contact}.
     * Participant is the owning entity, managing the foreign key 'contact_id' in its table.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    /**
     * List of {@link Transaction}s associated with this Participant.
     * Owned by Participant, defining the foreign key in Transaction.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private List<Transaction> transactions;


    /**
     * No-args constructor for JPA operations.
     */
    public Participant() {}

    public Participant(@NotBlank String username, @NotBlank String password, @NotBlank String alias) {
        super(username, password);
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
