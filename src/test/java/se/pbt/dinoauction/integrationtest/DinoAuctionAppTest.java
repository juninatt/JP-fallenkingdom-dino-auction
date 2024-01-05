package se.pbt.dinoauction.integrationtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Application:")
class DinoAuctionAppTest {

	@Test
	@DisplayName("Application context loaded successfully")
	void contextLoads() {
	}
}
