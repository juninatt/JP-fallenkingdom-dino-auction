package se.pbt.mrcoffee.unittest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import se.pbt.mrcoffee.controller.address.AddressController;
import se.pbt.mrcoffee.service.address.AddressService;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AddressControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
        objectMapper = new ObjectMapper();
    }

    @Nested
    class GetAddressTest {

        @Test
        @DisplayName("GET /address - Returns List of All Addresses")
        void getAllAddresses() throws Exception {
            var address = TestObjectFactory.createAddressResponseDTO(1L);
            when(addressService.getAllAddresses()).thenReturn(Collections.singletonList(address));

            mockMvc.perform(get("/address"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(address))));
        }

        @Test
        @DisplayName("GET /address - Returns List of All Addresses with Multiple Objects")
        void getAllAddresses_MultipleObjects() throws Exception {
            var address1 = TestObjectFactory.createAddressResponseDTO(1L);
            var address2 = TestObjectFactory.createAddressResponseDTO(2L);
            when(addressService.getAllAddresses()).thenReturn(Arrays.asList(address1, address2));

            mockMvc.perform(get("/address"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(address1, address2))));
        }

        @Test
        @DisplayName("GET /address - Returns Empty List When No Addresses")
        void getAllAddresses_EmptyDatabase() throws Exception {
            when(addressService.getAllAddresses()).thenReturn(Collections.emptyList());

            mockMvc.perform(get("/address"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(Collections.emptyList())));
        }
    }

    @Test
    @DisplayName("GET /address/{id} - Returns Address with given ID")
    void getAddressById() throws Exception {
        var address = TestObjectFactory.createAddressResponseDTO(1L);
        when(addressService.getAddressById(1L)).thenReturn(address);

        mockMvc.perform(get("/address/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(address)));
    }

    @Test
    @DisplayName("POST /address - Creates new Address")
    void createAddress() throws Exception {
        var address = TestObjectFactory.createAddressDTO();
        var returnedAddress = TestObjectFactory.createAddressResponseDTO(1L);
        when(addressService.createAddress(address)).thenReturn(returnedAddress);

        mockMvc.perform(post("/address")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(address)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PUT /address/{id} - Updates existing Address")
    void updateAddress() throws Exception {
        var address = TestObjectFactory.createAddressDTO();
        var returnedAddress = TestObjectFactory.createAddressResponseDTO(1L);
        when(addressService.updateAddress(1L, address)).thenReturn(returnedAddress);

        mockMvc.perform(put("/address/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(address)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /address/{id} - Deletes existing Address")
    void deleteAddress() throws Exception {
        doNothing().when(addressService).deleteAddress(1L);

        mockMvc.perform(delete("/address/1"))
                .andExpect(status().isNoContent());
    }
}
