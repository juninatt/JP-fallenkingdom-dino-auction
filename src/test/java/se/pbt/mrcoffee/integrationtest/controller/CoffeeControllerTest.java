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
import se.pbt.mrcoffee.integrationtest.testobject.TestObjectFactory;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.repository.product.CoffeeRepository;
import se.pbt.mrcoffee.service.product.CoffeeService;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    @DisplayName("GET /coffee - Returns List of All Coffees")
    void getAllCoffees() throws Exception {
        // Create a coffee object
        var coffee = TestObjectFactory.createCoffee();

        // Store the coffee object in the database
        coffeeService.createCoffee(coffee);

        // Perform a GET request to the "/coffee" endpoint and verify the response
        mockMvc.perform(get("/coffee"))
                .andExpect(status().isOk()) // Verify that the response status is 200 OK
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(coffee)))); // Verify that the response body contains the expected coffee object
    }

    @Test
    @DisplayName("GET /coffee/{id} - Returns Coffee with given ID")
    void getCoffeeById() throws Exception {
        // Create a coffee object
        var coffee = TestObjectFactory.createCoffee();

        // Store the coffee object in the database and get the generated ID
        var storedCoffee = coffeeService.createCoffee(coffee);

        // Perform a GET request to the "/coffee/{id}" endpoint with the generated ID and verify the response
        mockMvc.perform(get("/coffee/" + storedCoffee.getId()))
                .andExpect(status().isOk()) // Verify that the response status is 200 OK
                .andExpect(content().json(objectMapper.writeValueAsString(coffee))); // Verify that the response body contains the expected coffee object
    }

    @Test
    @DisplayName("POST /coffee - Creates new Coffee")
    void createCoffee() throws Exception {
        // Create a new coffee object and add properties
        Coffee coffee = new Coffee();

        // Perform a POST request to the "/coffee" endpoint with the coffee object and verify the response
        mockMvc.perform(post("/coffee")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(coffee)))
                .andExpect(status().isCreated()); // Verify that the response status is 201 Created
    }

    @Test
    @DisplayName("PUT /coffee/{id} - Updates existing Coffee")
    void updateCoffee() throws Exception {
        // Create a coffee object
        var coffee = TestObjectFactory.createCoffee();

        // Store the coffee object in the database
        var storedCoffee = coffeeService.createCoffee(coffee);

        // Create an updated coffee object
        var updatedCoffee = TestObjectFactory.createCoffee();
        updatedCoffee.setFlavorNotes("Disgusting");

        // Perform a PUT request to the "/coffee/{id}" endpoint with the updated coffee object and verify the response
        mockMvc.perform(put("/coffee/" + storedCoffee.getId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedCoffee)))
                .andExpect(status().isOk()); // Verify that the response status is 200 OK
    }

    @Test
    @DisplayName("DELETE /coffee/{id} - Deletes existing Coffee")
    void deleteCoffee() throws Exception {
        // Create a coffee object
        var coffee = TestObjectFactory.createCoffee();

        // Store the coffee object in the database
        var storedCoffee = coffeeService.createCoffee(coffee);

        // Perform a DELETE request to the "/coffee/{id}" endpoint with the generated ID and verify theresponse
        mockMvc.perform(delete("/coffee/" + storedCoffee.getId()))
                .andExpect(status().isNoContent()); // Verify that the response status is 204 No Content
    }
}
