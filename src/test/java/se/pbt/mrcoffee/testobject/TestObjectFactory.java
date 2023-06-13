package se.pbt.mrcoffee.testobject;

import se.pbt.mrcoffee.model.product.Coffee;

import java.math.BigDecimal;

public class TestObjectFactory {

    public static Coffee createCoffee(String name) {
        return new Coffee(
                name,
                "Description of " + name,
                BigDecimal.valueOf(9.99),
                "Origin of " + name,
                "Roast level of " + name,
                "Flavour notes of " + name,
                "Caffeine content of " + name);
    }
}
