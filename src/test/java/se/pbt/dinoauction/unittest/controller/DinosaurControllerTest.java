package se.pbt.dinoauction.unittest.controller;

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
import se.pbt.dinoauction.controller.DinosaurController;
import se.pbt.dinoauction.dto.DinosaurDTO;
import se.pbt.dinoauction.model.auctionitem.Dinosaur;
import se.pbt.dinoauction.service.DinosaurService;
import se.pbt.dinoauction.testobject.TestObjectFactory;

import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("DinosaurController Unit:")
class DinosaurControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper;
    @Mock
    private DinosaurService dinosaurService;
    @InjectMocks
    private DinosaurController dinosaurController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dinosaurController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Nested
    class GetAllDinosaursTest {

        @Test
        @DisplayName("Returns a list of all dinosaurs when dinosaurs are present")
        void return_allDinosaurs_whenDinosaursArePresent() throws Exception {
            // Create test coffee and mock service
            Dinosaur testDinosaur = TestObjectFactory.dinosaur();
            when(dinosaurService.getAllDinosaurs()).thenReturn(Collections.singletonList(testDinosaur));

            // Perform GET request and verify
            mockMvc.perform(get("/dinosaurs"))
                    .andExpect(status().isOk())  // 200 OK response expected
                    .andExpect(header().string("Content-Type", "application/json"))
                    .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(testDinosaur))));

            // Verify that the service method was called exactly once
            verify(dinosaurService, times(1)).getAllDinosaurs();
        }

        @Test
        @DisplayName("Returns an empty list when no dinosaurs are present")
        void return_emptyList_whenNoDinosaursArePresent() throws Exception {
            // Mock service to return empty list
            when(dinosaurService.getAllDinosaurs()).thenReturn(Collections.emptyList());

            // Perform the GET request and verify the response
            mockMvc.perform(get("/dinosaurs"))
                    .andExpect(status().isOk())  // 200 OK response expected
                    .andExpect(header().string("Content-Type", "application/json"))
                    .andExpect(content().json("[]"));  // Empty JSON array expected

            // Verify that the service method was called exactly once
            verify(dinosaurService, times(1)).getAllDinosaurs();
        }
    }


    @Nested
    class GetDinosaurByIdTest {

        @Test
        @DisplayName("Returns a dinosaur when the ID is valid")
        void return_dinosaur_whenIdIsValid() throws Exception {
            // Create a test Coffee object and set mock service behavior
            var testDinosaur = TestObjectFactory.dinosaur();
            testDinosaur.setId(1L);
            testDinosaur.setName("Lassie");
            when(dinosaurService.getDinosaurById(1L)).thenReturn(testDinosaur);

            // Perform the GET request and verify the response
            mockMvc.perform(get("/dinosaurs/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.name", is("Lassie")));

            // Verify that the service method was called exactly once with the valid ID
            verify(dinosaurService, times(1)).getDinosaurById(1L);
        }

        @Test
        @DisplayName("Returns 404 error when the ID is invalid")
        void return_404Error_whenIdIsInvalid() throws Exception {
            // Set mock service behavior to return null for an invalid ID
            when(dinosaurService.getDinosaurById(2L)).thenReturn(null);

            // Perform the GET request and verify the response
            mockMvc.perform(get("/dinosaurs/2"))
                    .andExpect(status().isNotFound());

            // Verify that the service method was called exactly once with the invalid ID
            verify(dinosaurService, times(1)).getDinosaurById(2L);
        }

        @Test
        @DisplayName("Returns HTTP 400 (Bad Request) for an invalid ID format")
        void should_Return400_When_BadFormat() throws Exception {
            // Perform the GET request with an invalid format and verify the response
            mockMvc.perform(get("/dinosaurs/invalid"))
                    .andExpect(status().isBadRequest());

            // Verify that no service methods were invoked
            verifyNoInteractions(dinosaurService);
        }
    }


    @Nested
    class CreateDinosaurTest {

        @Test
        @DisplayName("Invokes service method once and returns HTTP 201 Created")
        void should_InvokeService_When_Successful() throws Exception {
            // Create test CoffeeDTO and Coffee objects and set mock service behavior
            var dinosaurDTO = TestObjectFactory.dinosaurDTO();
            var testDinosaur = TestObjectFactory.dinosaur();
            when(dinosaurService.createDinosaur(any(DinosaurDTO.class))).thenReturn(testDinosaur);

            // Perform the POST request and verify response
            mockMvc.perform(post("/dinosaurs")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dinosaurDTO)))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(objectMapper.writeValueAsString(testDinosaur)));

            // Verify that the service method was called exactly once
            verify(dinosaurService, times(1)).createDinosaur(any(DinosaurDTO.class));
        }

        @Test
        @DisplayName("Returns created dinosaur with matching properties")
        void should_ReturnMatchingCoffee_When_Successful() throws Exception {
            // Create test CoffeeDTO and Coffee objects and set mock service behavior
            var dinosaurDTO = TestObjectFactory.dinosaurDTO();
            var testDinosaur = TestObjectFactory.dinosaur();
            when(dinosaurService.createDinosaur(any(DinosaurDTO.class))).thenReturn(testDinosaur);

            // Perform the POST request and verify response properties
            mockMvc.perform(post("/dinosaurs")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dinosaurDTO)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name", is(testDinosaur.getName())))
                    .andExpect(jsonPath("$.description", is(testDinosaur.getDescription())))
                    .andExpect(jsonPath("$.species", is(testDinosaur.getSpecies())))
                    .andExpect(jsonPath("$.gender", is(testDinosaur.getGender())))
                    .andExpect(jsonPath("$.kgWeight", is(testDinosaur.getKgWeight())))
                    .andExpect(jsonPath("$.dollarPrice", is(equalTo(1))));
            // Verify that the service method was called exactly once
            verify(dinosaurService, times(1)).createDinosaur(any(DinosaurDTO.class));
        }
    }


    @Nested
    class UpdateDinosaurTest {

        private DinosaurDTO dinosaurDTO;
        private Dinosaur testDinosaur;
        private final Long validId = 1L;
        private final Long invalidId = 2L;

        @BeforeEach
        void setUp() {
            // Arrange: Initialize test CoffeeDTO and Coffee objects
            dinosaurDTO = TestObjectFactory.dinosaurDTO();
            testDinosaur = TestObjectFactory.dinosaur();
        }

        @Test
        @DisplayName("Calls service method once with correct argument")
        void should_InvokeService_WithCorrectArg() throws Exception {
            // Set mock service behavior
            when(dinosaurService.updateDinosaur(validId, dinosaurDTO)).thenReturn(testDinosaur);

            // Perform the PUT request and verify service call
            mockMvc.perform(put("/dinosaurs/" + validId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dinosaurDTO)))
                    .andExpect(status().isOk());

            // Verify service method is called once with the correct argument
            verify(dinosaurService, times(1)).updateDinosaur(validId, dinosaurDTO);
        }

        @Test
        @DisplayName("Returns HTTP status OK (200) for valid update data")
        void returnsHttp200_ValidUpdate() throws Exception {
            // Set mock service behavior
            when(dinosaurService.updateDinosaur(validId, dinosaurDTO)).thenReturn(testDinosaur);

            // Perform the PUT request and verify HTTP status
            mockMvc.perform(put("/dinosaurs/" + validId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dinosaurDTO)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Returns updated dinosaur for valid update data")
        void returns_updatedDinosaur_whenValidUpdate() throws Exception {
            // Set mock service behavior
            when(dinosaurService.updateDinosaur(validId, dinosaurDTO)).thenReturn(testDinosaur);

            // Perform the PUT request and verify returned coffee details
            mockMvc.perform(put("/dinosaurs/" + validId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dinosaurDTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(testDinosaur.getName())))
                    .andExpect(jsonPath("$.description", is(testDinosaur.getDescription())))
                    .andExpect(jsonPath("$.species", is(testDinosaur.getSpecies())))
                    .andExpect(jsonPath("$.gender", is(testDinosaur.getGender())))
                    .andExpect(jsonPath("$.kgWeight", is(testDinosaur.getKgWeight())));
        }

        @Test
        @DisplayName("Returns 'not found' (404) for invalid ID")
        void should_ReturnHttp404_When_InvalidID() throws Exception {
            // Set mock service behavior for an invalid ID
            when(dinosaurService.updateDinosaur(invalidId, dinosaurDTO)).thenReturn(null);

            // Perform the PUT request and verify HTTP 404 status
            mockMvc.perform(put("/dinosaurs/" + invalidId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dinosaurDTO)))
                    .andExpect(status().isNotFound());
        }

    }


    @Nested
    class DeleteDinosaurTest {

        private final Long valid_Id = 1L;

        @Test
        @DisplayName("Calls service method once for valid ID and returns no content")
        void should_InvokeOnce_ReturnNoContent_When_ValidID() throws Exception {
            // Mock service behavior for a valid ID
            when(dinosaurService.deleteDinosaur(valid_Id)).thenReturn(true);

            // Perform DELETE request and verify HTTP 204 status
            mockMvc.perform(delete("/dinosaurs/" + valid_Id))
                    .andExpect(status().isNoContent());

            // Verify service method was called once
            verify(dinosaurService, times(1)).deleteDinosaur(valid_Id);
        }

        @Test
        @DisplayName("Returns 'no content' for successful delete and verifies service call")
        void should_ReturnHttp204_When_SuccessfulDelete() throws Exception {
            // Mock service behavior for a valid ID
            when(dinosaurService.deleteDinosaur(valid_Id)).thenReturn(true);

            // Perform DELETE request and verify HTTP 204 status
            MvcResult result = mockMvc.perform(delete("/dinosaurs/" + valid_Id))
                    .andExpect(status().isNoContent())
                    .andReturn();

            // Assert no error message present in response
            assertNull(result.getResponse().getErrorMessage());

            // Verify service method was called once
            verify(dinosaurService, times(1)).deleteDinosaur(valid_Id);
        }

        @Test
        @DisplayName("Returns 'not found' when no dinosaur exists to delete")
        void should_ReturnHttp404_When_NoDinosaurFound() throws Exception {
            // Mock service behavior for an invalid ID
            long invalid_Id = 2L;
            when(dinosaurService.deleteDinosaur(invalid_Id)).thenReturn(false);

            // Perform DELETE request and verify HTTP 404 status
            MvcResult result = mockMvc.perform(delete("/dinosaurs/" + invalid_Id))
                    .andExpect(status().isNotFound())
                    .andReturn();

            // Assert no error message present in response
            assertNull(result.getResponse().getErrorMessage());

            // Verify service method was called once
            verify(dinosaurService, times(1)).deleteDinosaur(invalid_Id);
        }
    }
}