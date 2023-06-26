package se.pbt.mrcoffee.enums;

public enum CustomerLevel {

    GUEST("Guest"),
    REGULAR("Regular"),
    PREMIUM("Premium"),
    VIP("VIP");

    private final String label;

    CustomerLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
