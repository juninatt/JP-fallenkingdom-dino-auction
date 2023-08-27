package se.pbt.mrcoffee.unittest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import se.pbt.mrcoffee.controller.product.CoffeeController;
import se.pbt.mrcoffee.dto.request.CoffeeDTO;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.service.product.CoffeeService;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("CoffeeController unit tests:")
class CoffeeControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper;
    @Mock
    private CoffeeService coffeeService;
    @InjectMocks
    private CoffeeController coffeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(coffeeController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Nested
    @DisplayName("GET /coffee - Endpoint Tests:")
    class GetAllCoffeesTest {

        @Test
        @DisplayName("GET /coffee invokes getAllCoffees service method once and returns expected content")
        void returnsExpectedValues_WhenObjectFound() throws Exception {
            // Create test coffee and mock service
            Coffee testCoffee = TestObjectFactory.createCoffee();
            when(coffeeService.getAllCoffees()).thenReturn(Collections.singletonList(testCoffee));

            // Perform GET request and verify
            mockMvc.perform(get("/coffee"))
                    .andExpect(status().isOk())  // 200 OK response expected
                    .andExpect(header().string("Content-Type", "application/json"))
                    .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(testCoffee))));

            // Verify that the service method was called exactly once
            verify(coffeeService, times(1)).getAllCoffees();
        }

        @Test
        @DisplayName("GET /coffee invokes getAllCoffees service method once and returns empty list when database is empty")
        void returnsEmptyList_WhenNoObjectIsFound() throws Exception {
            // Mock service to return empty list
            when(coffeeService.getAllCoffees()).thenReturn(Collections.emptyList());

            // Perform the GET request and verify the response
            mockMvc.perform(get("/coffee"))
                    .andExpect(status().isOk())  // 200 OK response expected
                    .andExpect(header().string("Content-Type", "application/json"))
                    .andExpect(content().json("[]"));  // Empty JSON array expected

            // Verify that the service method was called exactly once
            verify(coffeeService, times(1)).getAllCoffees();
        }
    }


    @Nested
    @DisplayName("GET /coffee/{id} - end point tests:")
    class GetCoffeeByIdTest {

        @Test
        @DisplayName("Invokes service method once for valid ID and returns correct content")
        void should_InvokeService_When_ValidId() throws Exception {
            // Create a test Coffee object and set mock service behavior
            var testCoffee = new Coffee();
            testCoffee.setId(1L);
            testCoffee.setName("Espresso");
            when(coffeeService.getCoffeeById(1L)).thenReturn(testCoffee);

            // Perform the GET request and verify the response
            mockMvc.perform(get("/coffee/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.name", is("Espresso")));

            // Verify that the service method was called exactly once with the valid ID
            verify(coffeeService, times(1)).getCoffeeById(1L);
        }

        @Test
        @DisplayName("Returns HTTP 404 Not Found for invalid ID and avoids calling service method")
        void should_Return404_When_InvalidId() throws Exception {
            // Set mock service behavior to return null for an invalid ID
            when(coffeeService.getCoffeeById(2L)).thenReturn(null);

            // Perform the GET request and verify the response
            mockMvc.perform(get("/coffee/2"))
                    .andExpect(status().isNotFound());

            // Verify that the service method was called exactly once with the invalid ID
            verify(coffeeService, times(1)).getCoffeeById(2L);
        }

        @Test
        @DisplayName("Returns HTTP 400 Bad Request for invalid ID format and avoids calling service method")
        void should_Return400_When_BadFormat() throws Exception {
            // Perform the GET request with an invalid format and verify the response
            mockMvc.perform(get("/coffee/invalid"))
                    .andExpect(status().isBadRequest());

            // Verify that no service methods were invoked
            verifyNoInteractions(coffeeService);
        }
    }


    @Nested
    @DisplayName("POST /coffee - endpoint tests:")
    class CreateCoffeeTest {

        @Test
        @DisplayName("Invokes service method once and returns HTTP 201 Created")
        void should_InvokeService_When_Successful() throws Exception {
            // Create test CoffeeDTO and Coffee objects and set mock service behavior
            var coffeeDTO = TestObjectFactory.createCoffeeDTO();
            var returnedCoffee = TestObjectFactory.createCoffee();
            when(coffeeService.createCoffee(any(CoffeeDTO.class))).thenReturn(returnedCoffee);

            // Perform the POST request and verify response
            mockMvc.perform(post("/coffee")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(coffeeDTO)))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(objectMapper.writeValueAsString(returnedCoffee)));

            // Verify that the service method was called exactly once
            verify(coffeeService, times(1)).createCoffee(any(CoffeeDTO.class));
        }

        @Test
        @DisplayName("Returns created coffee with matching properties")
        void should_ReturnMatchingCoffee_When_Successful() throws Exception {
            // Create test CoffeeDTO and Coffee objects and set mock service behavior
            var coffeeDTO = TestObjectFactory.createCoffeeDTO();
            var returnedCoffee = TestObjectFactory.createCoffee();
            when(coffeeService.createCoffee(any(CoffeeDTO.class))).thenReturn(returnedCoffee);

            // Perform the POST request and verify response properties
            mockMvc.perform(post("/coffee")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(coffeeDTO)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name", is(returnedCoffee.getName())))
                    .andExpect(jsonPath("$.description", is(returnedCoffee.getDescription())))
                    .andExpect(jsonPath("$.origin", is(returnedCoffee.getOrigin())))
                    .andExpect(jsonPath("$.roastLevel", is(returnedCoffee.getRoastLevel())))
                    .andExpect(jsonPath("$.flavorNotes", is(returnedCoffee.getFlavorNotes())))
                    .andExpect(jsonPath("$.caffeineContent", is(returnedCoffee.getCaffeineContent())));

            // Verify that the service method was called exactly once
            verify(coffeeService, times(1)).createCoffee(any(CoffeeDTO.class));
        }
    }


    @Nested
    @DisplayName("PUT /coffee/{id} - end point tests")
    class UpdateCoffeeTest {

        private CoffeeDTO coffeeDTO;
        private Coffee returnedCoffee;
        private final Long validId = 1L;
        private final Long invalidId = 2L;

        @BeforeEach
        void setUp() {
            // Arrange: Initialize test CoffeeDTO and Coffee objects
            coffeeDTO = TestObjectFactory.createCoffeeDTO();
            returnedCoffee = TestObjectFactory.createCoffee();
        }

        @Test
        @DisplayName("Calls service method once with correct argument")
        void should_InvokeService_WithCorrectArg() throws Exception {
            // Set mock service behavior
            when(coffeeService.updateCoffee(validId, coffeeDTO)).thenReturn(returnedCoffee);

            // Perform the PUT request and verify service call
            mockMvc.perform(put("/coffee/" + validId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(coffeeDTO)))
                    .andExpect(status().isOk());

            // Verify service method is called once with the correct argument
            verify(coffeeService, times(1)).updateCoffee(validId, coffeeDTO);
        }

        @Test
        @DisplayName("Returns HTTP status OK (200) for valid update data")
        void should_ReturnHttp200_When_ValidUpdate() throws Exception {
            // Set mock service behavior
            when(coffeeService.updateCoffee(validId, coffeeDTO)).thenReturn(returnedCoffee);

            // Perform the PUT request and verify HTTP status
            mockMvc.perform(put("/coffee/" + validId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(coffeeDTO)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Returns updated coffee for valid update data")
        void should_ReturnUpdatedCoffee_When_ValidUpdate() throws Exception {
            // Set mock service behavior
            when(coffeeService.updateCoffee(validId, coffeeDTO)).thenReturn(returnedCoffee);

            // Perform the PUT request and verify returned coffee details
            mockMvc.perform(put("/coffee/" + validId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(coffeeDTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(returnedCoffee.getName())))
                    .andExpect(jsonPath("$.description", is(returnedCoffee.getDescription())))
                    .andExpect(jsonPath("$.origin", is(returnedCoffee.getOrigin())));
        }

        @Test
        @DisplayName("Returns 'not found' (404) for invalid ID")
        void should_ReturnHttp404_When_InvalidID() throws Exception {
            // Set mock service behavior for an invalid ID
            when(coffeeService.updateCoffee(invalidId, coffeeDTO)).thenReturn(null);

            // Perform the PUT request and verify HTTP 404 status
            mockMvc.perform(put("/coffee/" + invalidId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(coffeeDTO)))
                    .andExpect(status().isNotFound());
        }

    }


    @Nested
    @DisplayName("DELETE /coffee/{id} - endpoint tests")
    class DeleteCoffeeTest {

        private final Long valid_Id = 1L;

        @Test
        @DisplayName("Calls service method once for valid ID and returns no content")
        void should_InvokeOnce_ReturnNoContent_When_ValidID() throws Exception {
            // Mock service behavior for a valid ID
            when(coffeeService.deleteCoffee(valid_Id)).thenReturn(true);

            // Perform DELETE request and verify HTTP 204 status
            mockMvc.perform(delete("/coffee/" + valid_Id))
                    .andExpect(status().isNoContent());

            // Verify service method was called once
            verify(coffeeService, times(1)).deleteCoffee(valid_Id);
        }

        @Test
        @DisplayName("Returns 'no content' for successful delete and verifies service call")
        void should_ReturnHttp204_When_SuccessfulDelete() throws Exception {
            // Mock service behavior for a valid ID
            when(coffeeService.deleteCoffee(valid_Id)).thenReturn(true);

            // Perform DELETE request and verify HTTP 204 status
            MvcResult result = mockMvc.perform(delete("/coffee/" + valid_Id))
                    .andExpect(status().isNoContent())
                    .andReturn();

            // Assert no error message present in response
            assertNull(result.getResponse().getErrorMessage());

            // Verify service method was called once
            verify(coffeeService, times(1)).deleteCoffee(valid_Id);
        }

        @Test
        @DisplayName("Returns 'not found' when no coffee exists to delete")
        void should_ReturnHttp404_When_NoCoffeeFound() throws Exception {
            // Mock service behavior for an invalid ID
            long invalid_Id = 2L;
            when(coffeeService.deleteCoffee(invalid_Id)).thenReturn(false);

            // Perform DELETE request and verify HTTP 404 status
            MvcResult result = mockMvc.perform(delete("/coffee/" + invalid_Id))
                    .andExpect(status().isNotFound())
                    .andReturn();

            // Assert no error message present in response
            assertNull(result.getResponse().getErrorMessage());

            // Verify service method was called once
            verify(coffeeService, times(1)).deleteCoffee(invalid_Id);
        }
    }
}