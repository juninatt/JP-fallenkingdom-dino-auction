package se.pbt.dinoauction.model.entity.transaction;

import jakarta.persistence.*;

/**
 * Represents the geographical drop-off coordinates for an auction transaction.
 * Each DropCoordinates instance is associated with a specific {@link Transaction}.
 */
@Entity
@Table(name = "drop_coordinates")
public class DropCoordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitude;

    /**
     * Represents the relationship to the {@link Transaction} connected to this DropCoordinates.
     * This entity is not the owner; the foreign key is maintained in the Transaction table.
     */
    @OneToOne(mappedBy = "dropCoordinates")
    private Transaction transaction;

    /**
     * No-args constructor for JPA operations.
     */
    public DropCoordinates() {
    }

    /**
     * Constructor to initialize latitude and longitude.
     *
     * @param latitude  The latitude coordinate of the drop-off location.
     * @param longitude The longitude coordinate of the drop-off location.
     */
    public DropCoordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}

