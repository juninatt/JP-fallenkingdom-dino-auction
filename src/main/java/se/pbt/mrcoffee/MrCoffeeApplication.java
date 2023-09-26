package se.pbt.mrcoffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Add validation before object creation
// TODO: Introduce constants for appropriate fields (for ex payment method in Payment)
@SpringBootApplication
public class MrCoffeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrCoffeeApplication.class, args);
	}

}
