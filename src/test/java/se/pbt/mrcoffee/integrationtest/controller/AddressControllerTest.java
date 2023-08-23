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
import se.pbt.mrcoffee.dto.request.AddressDTO;
import se.pbt.mrcoffee.integrationtest.testobject.TestObjectFactory;
import se.pbt.mrcoffee.repository.address.AddressRepository;
import se.pbt.mrcoffee.service.address.AddressService;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;


    @BeforeEach
    void setup() {
        // Clear the address data before each test
        addressRepository.deleteAll();
    }

    @Test
    @DisplayName("GET /address - Returns List of All Addresses")
    void getAllAddresses() throws Exception {
        // Arrange: Create and store an address
        var address = TestObjectFactory.createAddressDTO();
        addressService.createAddress(address);

        // Act and Assert: Perform GET request and validate the response
        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(address))));
    }

    @Test
    @DisplayName("GET /address/{id} - Returns Address with given ID")
    void getAddressById() throws Exception {
        // Arrange: Create and store an address
        var address = TestObjectFactory.createAddressDTO();
        var storedAddress = addressService.createAddress(address);

        // Act and Assert: Perform GET request and validate the response
        mockMvc.perform(get("/address/" + storedAddress.id()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(address)));
    }

    @Test
    @DisplayName("POST /address - Creates new Address")
    void createAddress() throws Exception {
        // Arrange: Create an address DTO
        var address = TestObjectFactory.createAddressDTO();

        // Act and Assert: Perform POST request and validate the response
        mockMvc.perform(post("/address")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(address)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PUT /address/{id} - Updates existing Address")
    void updateAddress() throws Exception {
        // Arrange: Create and store an address
        var address = TestObjectFactory.createAddressDTO();
        var storedAddress = addressService.createAddress(address);

        // Create an updated address DTO
        var updatedAddress = new AddressDTO(
                "Baker Street",
                221,
                0,
                "London",
                "NW1",
                "England"
        );


        // Act and Assert: Perform PUT request and validate the response
        mockMvc.perform(put("/address/" + storedAddress.id())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedAddress)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /address/{id} - Deletes existing Address")
    void deleteAddress() throws Exception {
        // Arrange: Create and store an address
        var address = TestObjectFactory.createAddressDTO();
        var storedAddress = addressService.createAddress(address);

        // Act and Assert: Perform DELETE request and validate the response
        mockMvc.perform(delete("/address/" + storedAddress.id()))
                .andExpect(status().isNoContent());
    }
}
