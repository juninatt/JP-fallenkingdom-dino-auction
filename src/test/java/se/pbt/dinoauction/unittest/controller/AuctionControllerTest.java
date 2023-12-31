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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import se.pbt.dinoauction.controller.AuctionController;
import se.pbt.dinoauction.exception.DinosaurNotFoundException;
import se.pbt.dinoauction.model.dto.DinoCardDataDTO;
import se.pbt.dinoauction.model.dto.DinoCardDataListDTO;
import se.pbt.dinoauction.model.dto.DinosaurDTO;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;
import se.pbt.dinoauction.service.AuctionService;
import se.pbt.dinoauction.testobject.TestObjectFactory;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("AuctionController Unit Tests:")
class AuctionControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper;
    @Mock
    private AuctionService auctionService;
    @InjectMocks
    private AuctionController auctionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(auctionController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Nested
    class GetAllDinosaursTest {

        @Test
        @DisplayName("Returns a list of all dinosaurs when dinosaurs are present")
        void return_allDinosaurs_whenDinosaursArePresent() throws Exception {
            // Create test object and mock service
            Dinosaur testDinosaur = TestObjectFactory.dinosaur();
            when(auctionService.getAllDinosaurs()).thenReturn(Collections.singletonList(testDinosaur));

            // Perform GET request and verify
            mockMvc.perform(get("/dinosaurs"))
                    .andExpect(status().isOk())  // 200 OK response expected
                    .andExpect(header().string("Content-Type", "application/json"))
                    .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(testDinosaur))));

            // Verify that the service method was called exactly once
            verify(auctionService, times(1)).getAllDinosaurs();
        }

        @Test
        @DisplayName("Returns an empty list when no dinosaurs are present")
        void return_emptyList_whenNoDinosaursArePresent() throws Exception {
            // Mock service to return empty list
            when(auctionService.getAllDinosaurs()).thenReturn(Collections.emptyList());

            // Perform the GET request and verify the response
            mockMvc.perform(get("/dinosaurs"))
                    .andExpect(status().isOk())  // 200 OK response expected
                    .andExpect(header().string("Content-Type", "application/json"))
                    .andExpect(content().json("[]"));  // Empty JSON array expected

            // Verify that the service method was called exactly once
            verify(auctionService, times(1)).getAllDinosaurs();
        }
    }


    @Nested
    class GetDinosaurByIdTest {

        @Test
        @DisplayName("Returns a dinosaur when the ID is valid")
        void return_dinosaur_whenIdIsValid() throws Exception {
            // Create a test object and set mock service behavior
            var testDinosaur = TestObjectFactory.dinosaur();
            testDinosaur.setId(1L);
            testDinosaur.setName("Lassie");
            when(auctionService.getDinosaurById(1L)).thenReturn(testDinosaur);

            // Perform the GET request and verify the response
            mockMvc.perform(get("/dinosaurs/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.name", is("Lassie")));

            // Verify that the service method was called exactly once with the valid ID
            verify(auctionService, times(1)).getDinosaurById(1L);
        }

        @Test
        @DisplayName("Returns 404 error when the ID is invalid")
        void return_404Error_whenIdIsInvalid() throws Exception {
            // Set mock service behavior to return null for an invalid ID
            when(auctionService.getDinosaurById(2L)).thenReturn(null);

            // Perform the GET request and verify the response
            mockMvc.perform(get("/dinosaurs/2"))
                    .andExpect(status().isNotFound());

            // Verify that the service method was called exactly once with the invalid ID
            verify(auctionService, times(1)).getDinosaurById(2L);
        }

        @Test
        @DisplayName("Returns HTTP 400 (Bad Request) for an invalid ID format")
        void should_Return400_When_BadFormat() throws Exception {
            // Perform the GET request with an invalid format and verify the response
            mockMvc.perform(get("/dinosaurs/invalid"))
                    .andExpect(status().isBadRequest());

            // Verify that no service methods were invoked
            verifyNoInteractions(auctionService);
        }
    }


    @Nested
    class CreateDinosaurTest {

        @Test
        @DisplayName("Invokes service method once and returns HTTP 201 Created")
        void should_InvokeService_When_Successful() throws Exception {
            // Create test DTO and entity objects and set mock service behavior
            var dinosaurDTO = TestObjectFactory.dinosaurDTO();
            var testDinosaur = TestObjectFactory.dinosaur();
            when(auctionService.createDinosaur(any(DinosaurDTO.class))).thenReturn(testDinosaur);

            // Perform the POST request and verify response
            mockMvc.perform(post("/dinosaurs")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dinosaurDTO)))
                    .andExpect(status().isCreated())
                    .andExpect(content().json(objectMapper.writeValueAsString(testDinosaur)));

            // Verify that the service method was called exactly once
            verify(auctionService, times(1)).createDinosaur(any(DinosaurDTO.class));
        }

        @Test
        @DisplayName("Returns created dinosaur with matching properties")
        void should_ReturnMatchingCoffee_When_Successful() throws Exception {
            // Create test DTO and entity objects and set mock service behavior
            var dinosaurDTO = TestObjectFactory.dinosaurDTO();
            var testDinosaur = TestObjectFactory.dinosaur();
            when(auctionService.createDinosaur(any(DinosaurDTO.class))).thenReturn(testDinosaur);

            // Perform the POST request and verify response properties
            mockMvc.perform(post("/dinosaurs")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dinosaurDTO)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name", is(testDinosaur.getName())))
                    .andExpect(jsonPath("$.description", is(testDinosaur.getDescription())))
                    .andExpect(jsonPath("$.species", is(testDinosaur.getSpecies())))
                    .andExpect(jsonPath("$.gender", is(testDinosaur.getGender())))
                    .andExpect(jsonPath("$.weightInKg", is(testDinosaur.getWeightInKg())))
                    .andExpect(jsonPath("$.priceInDollar", is(equalTo(1))));
            // Verify that the service method was called exactly once
            verify(auctionService, times(1)).createDinosaur(any(DinosaurDTO.class));
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
            // Arrange: Initialize test DTO and entity objects
            dinosaurDTO = TestObjectFactory.dinosaurDTO();
            testDinosaur = TestObjectFactory.dinosaur();
        }

        @Test
        @DisplayName("Calls service method once with correct argument")
        void should_InvokeService_WithCorrectArg() throws Exception {
            // Set mock service behavior
            when(auctionService.updateDinosaur(validId, dinosaurDTO)).thenReturn(testDinosaur);

            // Perform the PUT request and verify service call
            mockMvc.perform(put("/dinosaurs/" + validId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dinosaurDTO)))
                    .andExpect(status().isOk());

            // Verify service method is called once with the correct argument
            verify(auctionService, times(1)).updateDinosaur(validId, dinosaurDTO);
        }

        @Test
        @DisplayName("Returns HTTP status OK (200) for valid update data")
        void returnsHttp200_ValidUpdate() throws Exception {
            // Set mock service behavior
            when(auctionService.updateDinosaur(validId, dinosaurDTO)).thenReturn(testDinosaur);

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
            when(auctionService.updateDinosaur(validId, dinosaurDTO)).thenReturn(testDinosaur);

            // Perform the PUT request and verify returned object details
            mockMvc.perform(put("/dinosaurs/" + validId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(dinosaurDTO)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(testDinosaur.getName())))
                    .andExpect(jsonPath("$.description", is(testDinosaur.getDescription())))
                    .andExpect(jsonPath("$.species", is(testDinosaur.getSpecies())))
                    .andExpect(jsonPath("$.gender", is(testDinosaur.getGender())))
                    .andExpect(jsonPath("$.weightInKg", is(testDinosaur.getWeightInKg())));
        }

        @Test
        @DisplayName("Returns 'not found' (404) for invalid ID")
        void should_ReturnHttp404_When_InvalidID() throws Exception {
            // Set mock service behavior for an invalid ID
            when(auctionService.updateDinosaur(invalidId, dinosaurDTO)).thenReturn(null);

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
            when(auctionService.deleteDinosaur(valid_Id)).thenReturn(true);

            // Perform DELETE request and verify HTTP 204 status
            mockMvc.perform(delete("/dinosaurs/" + valid_Id))
                    .andExpect(status().isNoContent());

            // Verify service method was called once
            verify(auctionService, times(1)).deleteDinosaur(valid_Id);
        }

        @Test
        @DisplayName("Returns 'no content' for successful delete and verifies service call")
        void should_ReturnHttp204_When_SuccessfulDelete() throws Exception {
            // Mock service behavior for a valid ID
            when(auctionService.deleteDinosaur(valid_Id)).thenReturn(true);

            // Perform DELETE request and verify HTTP 204 status
            mockMvc.perform(delete("/dinosaurs/" + valid_Id))
                    .andExpect(status().isNoContent())
                    .andReturn();

            // Verify service method was called once
            verify(auctionService, times(1)).deleteDinosaur(valid_Id);
        }

        @Test
        @DisplayName("Returns 'not found' when no dinosaur exists to delete")
        void should_ReturnHttp404_When_NoDinosaurFound() throws Exception {
            // Mock service behavior for an invalid ID
            long invalid_Id = 2L;
            when(auctionService.deleteDinosaur(invalid_Id)).thenReturn(false);

            // Perform DELETE request and verify HTTP 404 status
            mockMvc.perform(delete("/dinosaurs/" + invalid_Id))
                    .andExpect(status().isNotFound())
                    .andReturn();

            // Verify service method was called once
            verify(auctionService, times(1)).deleteDinosaur(invalid_Id);
        }
    }

    @Nested
    class GetDinoCardDataListTest {

        @Test
        @DisplayName("Returns a list of all DinoCardData")
        void return_allDinoCardData_adminAccess() throws Exception {
            // Create and store a DinoCardDataDTO object for testing
            DinoCardDataDTO dinoCardData = TestObjectFactory.dinoCardDataDTO();
            DinoCardDataListDTO cardDataList = new DinoCardDataListDTO(List.of(dinoCardData));

            when(auctionService.getDinoCardDataList()).thenReturn(cardDataList);

            // Perform the GET request and validate the response
            mockMvc.perform(get("/dinosaurs/dino-cards"))
                    .andExpect(status().isOk())
                    .andExpect(header().string("Content-Type", "application/json"))
                    .andExpect(content().json(objectMapper.writeValueAsString(cardDataList)));
        }

        @Test
        @DisplayName("Returns 404 when no dinosaurs are present in the database")
        void return_http404_whenNoDinosaurInDatabase_adminAccess() throws Exception {
            when(auctionService.getDinoCardDataList()).thenThrow(new DinosaurNotFoundException());

            // Perform the GET request and validate the response
            mockMvc.perform(get("/dinosaurs/dino-cards"))
                    .andExpect(status().isNotFound());  // Expect a 404 NOT FOUND
        }
    }
}