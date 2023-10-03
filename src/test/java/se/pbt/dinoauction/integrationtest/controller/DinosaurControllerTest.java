package se.pbt.dinoauction.integrationtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.pbt.dinoauction.repository.auctionitem.DinosaurRepository;
import se.pbt.dinoauction.service.DinosaurService;
import se.pbt.dinoauction.testobject.TestObjectFactory;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("DinosaurController Integration Tests:")
public class DinosaurControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DinosaurRepository dinosaurRepository;
    @Autowired
    private DinosaurService dinosaurService;

    @BeforeEach
    void setup() {
        dinosaurRepository.deleteAll();
    }


    @Nested
    @WithMockUser(roles = {"ADMIN"})
    class AdminAccessTest {


        @Test
        @DisplayName("Returns a list of all dinosaurs when accessed by an ADMIN")
        void return_allDinosaurs_adminAccess() throws Exception {
            // Create and store a Dinosaur object for testing
            var dinosaurDTO = TestObjectFactory.dinosaurDTO();
            var testDinosaur = dinosaurService.createDinosaur(dinosaurDTO);

            // Fetch the stored Dinosaur objects and validate their properties
            var existingDinosaur = dinosaurRepository.findAll()
                    .iterator()
                    .next();

            assertEquals(testDinosaur.getName(), existingDinosaur.getName(), "The stored Dinosaur should have the expected name");

            // Perform the GET request and validate the response
            mockMvc.perform(get("/dinosaurs"))
                    .andExpect(status().isOk())  // (200 OK)
                    .andExpect(header().string("Content-Type", "application/json"))
                    .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(testDinosaur))));

            // Assert the state of the database remains consistent after the operation
            assertDatabase(1, "The database should still contain one coffee entry");
        }


        @Test
        @DisplayName("Returns a specific dinosaur based on its ID when accessed by an ADMIN")
        void return_dinosaurById_adminAccess() throws Exception {
            // Create and store a Dinosaur object to test on
            var dinosaurDTO = TestObjectFactory.dinosaurDTO();
            var testDinosaur = dinosaurService.createDinosaur(dinosaurDTO);

            // Fetch the stored Dinosaur object and validate its properties
            var existingDinosaur = dinosaurRepository.findAll()
                    .iterator().next();
            assertEquals(dinosaurDTO.name(), existingDinosaur.getName(), "The Dinosaur name should be updated");

            // Perform GET request and validate response
            mockMvc.perform(get("/dinosaurs/" + testDinosaur.getId()))
                    .andExpect(status().isOk())  // (200 OK)
                    .andExpect(header().string("Content-Type", "application/json"))
                    .andExpect(content().json(objectMapper.writeValueAsString(testDinosaur)));

            // Assert that the database remains in the expected state after the operation
            assertDatabase(1, "The database should still contain one Dinosaur entry");
        }

        @Test
        @DisplayName("Creates a new dinosaur when accessed by an ADMIN")
        void create_newDinosaur_adminAccess() throws Exception {
            // Create a Dinosaur object for the POST request
            var dinosaurDTO = TestObjectFactory.dinosaurDTO();

            // Perform a POST request to create a new Dinosaur object
            mockMvc.perform(post("/dinosaurs")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(dinosaurDTO)))
                    .andExpect(status().isCreated());  // (201 Created)


            // Fetch the stored Dinosaur from the database
            var updatedDinosaur = dinosaurRepository.findAll()
                    .iterator()
                    .next();

            // Assert that a Dinosaur object has been stored
            assertDatabase(1, "There should be one Dinosaur entry in the database");

            // Assert that the stored coffee has the expected properties
            assertEquals(dinosaurDTO.name(), updatedDinosaur.getName(), "The Dinosaur name should be updated");
        }


        @Test
        @DisplayName("Updates an existing dinosaur when accessed by an ADMIN")
        void update_existingDinosaur_adminAccess() throws Exception {
            // Create and stores a Dinosaur object
            var dinosaurDTO = TestObjectFactory.dinosaurDTO();
            var testDinosaur = dinosaurService.createDinosaur(dinosaurDTO);

            // Create an updated Dinosaur object
            var updatingDTO = TestObjectFactory.dinosaurDTO();

            // Perform a PUT request to update the Dinosaur object
            mockMvc.perform(put("/dinosaurs/" + testDinosaur.getId())
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(updatingDTO)))
                    .andExpect(status().isOk());  // (200 OK)

            // Fetch the updated Dinosaur from the database
            var updatedDinosaur = dinosaurService.getDinosaurById(testDinosaur.getId());

            // Assert that the Dinosaur object has been updated
            assertEquals(updatingDTO.name(), updatedDinosaur.getName(), "The Dinosaur name should be updated");

            // Assert that the database still has only one record
            assertDatabase(1, "There should still be one Dinosaur stored");
        }


        @Test
        @DisplayName("Deletes an existing dinosaur when accessed by an ADMIN")
        void delete_existingDinosaur_adminAccess() throws Exception {
            // Create and store a Dinosaur object
            var dinosaurDTO = TestObjectFactory.dinosaurDTO();
            var testDinosaur = dinosaurService.createDinosaur(dinosaurDTO);

            // Perform a DELETE request with the generated ID and verify the response
            mockMvc.perform(delete("/dinosaurs/" + testDinosaur.getId()))
                    .andExpect(status().isNoContent());  // (204 No Content)

            // Assert the state of the database after the DELETE operation
            assertDatabase(0, "The database should be empty after the delete operation");
        }
    }

    private void assertDatabase(int expected, String message) {
        assertEquals(expected, dinosaurRepository.count(), message);
    }
}
