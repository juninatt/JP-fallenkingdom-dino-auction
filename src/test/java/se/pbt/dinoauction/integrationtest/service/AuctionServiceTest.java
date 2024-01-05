package se.pbt.dinoauction.integrationtest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import se.pbt.dinoauction.exception.DinosaurNotFoundException;
import se.pbt.dinoauction.jms.MessageProducer;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;
import se.pbt.dinoauction.repository.DinosaurRepository;
import se.pbt.dinoauction.service.AuctionService;
import se.pbt.dinoauction.testobject.TestObjectFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Dinosaur Service Integration Tests")
class AuctionServiceTest {

    @Autowired
    private DinosaurRepository dinosaurRepository;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private AuctionService auctionService;

    @BeforeEach
    void setupDatabaseWithTestDinosaurs() {
        dinosaurRepository.deleteAll();
        dinosaurRepository.saveAll(List.of(
                TestObjectFactory.dinosaur(),
                TestObjectFactory.dinosaur(),
                TestObjectFactory.dinosaur(),
                TestObjectFactory.dinosaur(),
                TestObjectFactory.dinosaur()
        ));
    }

    @Test
    @DisplayName("Verifies all items are of type dinosaur")
    void verifies_typeOfAllRetrievedItems() {
        var result = auctionService.getAllDinosaurs();
        assertNotNull(result);
        result.forEach(dinosaur -> assertEquals(Dinosaur.class, dinosaur.getClass()));
    }

    @Test
    @DisplayName("Returns all dinosaurs in database")
    void returns_allObjects_fromDatabase() {
        var result = auctionService.getAllDinosaurs();
        assertNotNull(result);
        assertEquals(5, result.size());
    }

    @Test
    @DisplayName("Throws exception if no dinosaurs exist in database")
    void throws_exceptionIfNoDinosaursFound() {
        dinosaurRepository.deleteAll();
        assertTrue(dinosaurRepository.findAll().isEmpty());
        assertThrows(DinosaurNotFoundException.class, () -> auctionService.getAllDinosaurs());
    }

    @Test
    @DisplayName("Updates dinosaur when ID is found")
    void updates_dinosaur_whenIdExists() {
        long dinosaurId = 1L;
        var dinosaurDTO = TestObjectFactory.dinosaurDTO();
        var existingDinosaur = TestObjectFactory.dinosaur();

        DinosaurRepository dinosaurRepositoryMock = mock(DinosaurRepository.class);
        when(dinosaurRepositoryMock.findById(dinosaurId)).thenReturn(Optional.of(existingDinosaur));
        when(dinosaurRepositoryMock.save(existingDinosaur)).thenReturn(existingDinosaur);

        MessageProducer messageProducerMock = mock(MessageProducer.class);

        AuctionService auctionServiceMock = new AuctionService(dinosaurRepositoryMock, messageProducerMock);

        Dinosaur result = auctionServiceMock.updateDinosaur(dinosaurId, dinosaurDTO);

        assertNotNull(result);
        assertEquals(dinosaurDTO.name(), result.getName());
        verify(messageProducerMock).sendMessage("myQueue", "Dinosaur updated: " + result.getName());
    }

    @Test
    @DisplayName("Returns null when dinosaur ID is not found")
    void returns_null_whenDinosaurIdDoesNotExist() {
        long nonExistingDinosaurId = 999L;
        DinosaurRepository dinosaurRepositoryMock = mock(DinosaurRepository.class);
        when(dinosaurRepositoryMock.findById(nonExistingDinosaurId)).thenReturn(Optional.empty());

        MessageProducer messageProducerMock = mock(MessageProducer.class);

        AuctionService auctionServiceMock = new AuctionService(dinosaurRepositoryMock, messageProducerMock);

        Dinosaur result = auctionServiceMock.updateDinosaur(nonExistingDinosaurId, TestObjectFactory.dinosaurDTO());

        assertNull(result);
        verifyNoInteractions(messageProducerMock);
    }
}
