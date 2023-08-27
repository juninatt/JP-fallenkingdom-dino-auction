package se.pbt.mrcoffee.integrationtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.pbt.mrcoffee.repository.product.CoffeeRepository;
import se.pbt.mrcoffee.service.product.CoffeeService;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class CoffeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeService coffeeService;

    @BeforeEach
    void setup() {
        coffeeRepository.deleteAll();
    }


    @Test
    @DisplayName("GET /coffee - Returns list of all coffees")
    void testEndPoint_getCoffee() throws Exception {
        // Assert initial state of the database
        assertEquals(0, coffeeRepository.count(), "The database should initially be empty");

        // Create and store a CoffeeDTO object for testing
        var coffeeDTO = TestObjectFactory.createCoffeeDTO();
        var createdCoffee = coffeeService.createCoffee(coffeeDTO);

        // Verify that the coffee object was correctly stored
        assertEquals(1, coffeeRepository.count(), "There should be one coffee entry in the database");

        // Fetch the stored coffee objects and validate their properties
        var storedCoffees = coffeeRepository.findAll();
        var storedCoffee = storedCoffees.iterator().next();
        assertEquals(createdCoffee.getName(), storedCoffee.getName(), "The stored coffee should have the expected name");

        // Perform the GET request and validate the response
        mockMvc.perform(get("/coffee"))
                .andExpect(status().isOk())  // (200 OK)
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(coffeeDTO))));

        // Assert the state of the database remains consistent after the operation
        assertEquals(1, coffeeRepository.count(), "The database should still contain one coffee entry");
    }


    @Test
    @DisplayName("GET /coffee/{id} - Returns coffee with given ID")
    void testEndPoint_getCoffeeById() throws Exception {
        // Assert initial state of database
        assertEquals(0, coffeeRepository.count(), "The database should initially be empty");

        // Create and store a CoffeeDTO object to test on
        var coffeeDTO = TestObjectFactory.createCoffeeDTO();
        var createdCoffee = coffeeService.createCoffee(coffeeDTO);

        // Verify that the Coffee object was correctly stored
        assertEquals(1, coffeeRepository.count(), "There should be one coffee entry in the database");

        // Fetch the stored Coffee object and validate its properties
        var storedCoffees = coffeeRepository.findAll();
        var storedCoffee = storedCoffees.iterator().next();
        assertEquals(coffeeDTO.flavorNotes(), storedCoffee.getFlavorNotes(), "The stored coffee should have the expected flavor notes");

        // Perform GET request and validate response
        mockMvc.perform(get("/coffee/" + createdCoffee.getId()))
                .andExpect(status().isOk())  // (200 OK)
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(content().json(objectMapper.writeValueAsString(coffeeDTO)));

        // Assert that the database remains in the expected state after the operation
        assertEquals(1, coffeeRepository.count(), "The database should still contain one coffee entry");
    }



    @Test
    @DisplayName("POST /coffee - Creates and stores new Coffee object to database")
    void createCoffee() throws Exception {
        // Assert the initial state of the database
        assertEquals(0, coffeeRepository.count(), "The database should be empty initially");

        // Create a CoffeeDTO object for the POST request
        var coffeeDTO = TestObjectFactory.createCoffeeDTO();

        // Perform a POST request to create a new Coffee object
        mockMvc.perform(post("/coffee")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(coffeeDTO)))
                .andExpect(status().isCreated());  // (201 Created)

        // Assert that a coffee object has been stored
        assertEquals(1, coffeeRepository.count(), "There should be one coffee entry in the database");

        // Fetch the stored coffee from the database
        var storedCoffees = coffeeRepository.findAll();
        var storedCoffee = storedCoffees.iterator().next();

        // Assert that the stored coffee has the expected properties
        assertEquals(coffeeDTO.flavorNotes(), storedCoffee.getFlavorNotes(), "The stored coffee should have the expected flavor notes");
    }


    @Test
    @DisplayName("PUT /coffee/{id} - Updates existing Coffee object")
    void updateCoffee() throws Exception {
        // Assert the database state before any operation
        assertEquals(0, coffeeRepository.count(), "The database should be empty initially");

        // Create and stores a coffee object
        var coffeeDTO = TestObjectFactory.createCoffeeDTO();
        var storedCoffee = coffeeService.createCoffee(coffeeDTO);

        // Assert that one coffee object has been stored
        assertEquals(1, coffeeRepository.count(), "One coffee should be stored");

        // Create an updated coffee object
        var updatingDTO = TestObjectFactory.createCoffeeDTO();

        // Perform a PUT request to update the coffee object
        mockMvc.perform(put("/coffee/" + storedCoffee.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatingDTO)))
                .andExpect(status().isOk());  // (200 OK)

        // Fetch the updated coffee from the database
        var fetchedUpdatedCoffee = coffeeService.getCoffeeById(storedCoffee.getId());

        // Assert that the coffee object has been updated
        assertEquals(updatingDTO.flavorNotes(), fetchedUpdatedCoffee.getFlavorNotes(), "The coffee flavor notes should be updated");

        // Assert that the database still has only one record
        assertEquals(1, coffeeRepository.count(), "There should still be one coffee stored");
    }


    @Test
    @DisplayName("DELETE /coffee/{id} - Deletes existing Coffee")
    void deleteCoffee() throws Exception {
        // Assert the database state before any operation
        assertEquals(0, coffeeRepository.count(), "The database should be empty initially");

        // Create and store a coffee object
        var coffeeDTO = TestObjectFactory.createCoffeeDTO();
        var storedCoffee = coffeeService.createCoffee(coffeeDTO);

        // Assert that the coffee object has been stored
        assertEquals(1, coffeeRepository.count(), "One coffee should be stored");

        // Perform a DELETE request with the generated ID and verify the response
        mockMvc.perform(delete("/coffee/" + storedCoffee.getId()))
                .andExpect(status().isNoContent());  // (204 No Content)

        // Assert the state of the database after the DELETE operation
        assertEquals(0, coffeeRepository.count(), "The database should be empty after the delete operation");
    }

}
