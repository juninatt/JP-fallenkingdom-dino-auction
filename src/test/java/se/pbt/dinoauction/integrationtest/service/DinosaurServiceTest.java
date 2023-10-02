package se.pbt.dinoauction.integrationtest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.dinoauction.exception.DinosaurNotFoundException;
import se.pbt.dinoauction.jms.JmsMessageProducer;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;
import se.pbt.dinoauction.repository.auctionitem.DinosaurRepository;
import se.pbt.dinoauction.service.DinosaurService;
import se.pbt.dinoauction.testobject.TestObjectFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Dinosaur Service Integration Tests")
class DinosaurServiceTest {

    @Autowired
    private DinosaurRepository dinosaurRepository;

    @Autowired
    private JmsMessageProducer jmsMessageProducer;

    @Autowired
    private DinosaurService dinosaurService;

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
        var result = dinosaurService.getAllDinosaurs();
        assertNotNull(result);
        result.forEach(dinosaur -> assertEquals(Dinosaur.class, dinosaur.getClass()));
    }

    @Test
    @DisplayName("Returns all dinosaurs in database")
    void returns_allObjects_fromDatabase() {
        var result = dinosaurService.getAllDinosaurs();
        assertNotNull(result);
        assertEquals(5, result.size());
    }

    @Test
    @DisplayName("Throws exception if no dinosaurs exist in database")
    void throws_exceptionIfNoDinosaursFound() {
        dinosaurRepository.deleteAll();
        assertTrue(dinosaurRepository.findAll().isEmpty());
        assertThrows(DinosaurNotFoundException.class, () -> dinosaurService.getAllDinosaurs());
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

        JmsMessageProducer jmsMessageProducerMock = mock(JmsMessageProducer.class);

        DinosaurService dinosaurServiceMock = new DinosaurService(dinosaurRepositoryMock, jmsMessageProducerMock);

        Dinosaur result = dinosaurServiceMock.updateDinosaur(dinosaurId, dinosaurDTO);

        assertNotNull(result);
        assertEquals(dinosaurDTO.name(), result.getName());
        verify(jmsMessageProducerMock).sendMessage("myQueue", "Dinosaur updated: " + result.getName());
    }

    @Test
    @DisplayName("Returns null when dinosaur ID is not found")
    void returns_null_whenDinosaurIdDoesNotExist() {
        long nonExistingDinosaurId = 999L;
        DinosaurRepository dinosaurRepositoryMock = mock(DinosaurRepository.class);
        when(dinosaurRepositoryMock.findById(nonExistingDinosaurId)).thenReturn(Optional.empty());

        JmsMessageProducer jmsMessageProducerMock = mock(JmsMessageProducer.class);

        DinosaurService dinosaurServiceMock = new DinosaurService(dinosaurRepositoryMock, jmsMessageProducerMock);

        Dinosaur result = dinosaurServiceMock.updateDinosaur(nonExistingDinosaurId, TestObjectFactory.dinosaurDTO());

        assertNull(result);
        verifyNoInteractions(jmsMessageProducerMock);
    }
}
