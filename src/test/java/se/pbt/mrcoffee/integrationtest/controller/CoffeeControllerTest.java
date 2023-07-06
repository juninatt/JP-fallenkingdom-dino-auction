package se.pbt.mrcoffee.integrationtest.controller;

import se.pbt.mrcoffee.integrationtest.testobject.TestObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.pbt.mrcoffee.controller.product.CoffeeController;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.service.product.CoffeeService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName("CoffeeController:")
class CoffeeControllerTest {

    @Mock
    private CoffeeService coffeeService;
    @InjectMocks
    private CoffeeController coffeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("getAllCoffees()")
    class GetAllCoffeesTest {

        @Test
        @DisplayName("Calls service method once")
        void getAllCoffees_callsServiceMethodOnce() {
            // Create a test coffee object to use as a mock
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object
            when(coffeeService.getAllCoffees()).thenReturn(Collections.singletonList(coffee));

            // Call method under test
            coffeeController.getAllCoffees();

            // Verify that the getAllCoffees method in the coffee service layer was called
            verify(coffeeService, times(1)).getAllCoffees();
        }

        @Test
        @DisplayName("Returns HTTP status code 200 (OK) when coffees are available")
        void getAllCoffees_returnsResponseStatus200_whenCoffeesAreAvailable() {
            // Create a test coffee object to use as a mock
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object
            when(coffeeService.getAllCoffees()).thenReturn(Collections.singletonList(coffee));

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.getAllCoffees();

            // Assert the HTTP status code
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        @DisplayName("Returns the coffee object in the response")
        void getAllCoffees_returnsCoffeeInResponseEntity_whenCoffeesAreAvailable() {
            // Create a test coffee object to use as a mock
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object
            when(coffeeService.getAllCoffees()).thenReturn(Collections.singletonList(coffee));

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.getAllCoffees();

            // Assert the mock coffee object in the response body
            assertEquals(Collections.singletonList(coffee), response.getBody());
        }

        @Test
        @DisplayName("Calls method in service layer and returns expected values")
        void getAllCoffees_returnsCorrectValues_whenCoffeesAreAvailable() {
            // Create a test coffee object to use as a mock
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object
            when(coffeeService.getAllCoffees()).thenReturn(Collections.singletonList(coffee));

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.getAllCoffees();

            // Assert the HTTP status code
            assertEquals(HttpStatus.OK, response.getStatusCode());

            // Assert the mock coffee object in the response body
            assertEquals(Collections.singletonList(coffee), response.getBody());

            // Verify that the getAllCoffees method in the coffee service layer was called
            verify(coffeeService).getAllCoffees();
        }


        @Test
        @DisplayName("Returns empty list when no coffees are available")
        void testGetAllCoffeesEmpty() {
            // Mock the behavior of the coffee service layer to return an empty list of coffees
            when(coffeeService.getAllCoffees()).thenReturn(Collections.emptyList());

            // Call the method under test and assign the returned value to a response variable
            ResponseEntity<List<Coffee>> response = coffeeController.getAllCoffees();

            // Assert the HTTP status code is OK
            assertEquals(HttpStatus.OK, response.getStatusCode());

            // Assert that the response body is empty
            assertTrue(Objects.requireNonNull(response.getBody()).isEmpty());

            // Verify that the getAllCoffees method in the coffee service layer was called
            verify(coffeeService).getAllCoffees();
        }
    }

    @Nested
    @DisplayName("getCoffeeById()")
    class GetCoffeeById {

        @Test
        @DisplayName("Calls service method once when ID is valid")
        void getCoffeeById_callsServiceMethodOnce_whenValidId() {
            // Mock the behavior of the coffee service layer to return a coffee object when getCoffeeById is called with a valid ID
            var coffee = new Coffee();
            when(coffeeService.getCoffeeById(1L)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.getCoffeeById(1L);

            // Verify that the getCoffeeById method in the coffee service layer was called with the valid ID
            verify(coffeeService, times(1)).getCoffeeById(1L);
        }

        @Test
        @DisplayName("Calls service method once when ID is invalid")
        void getCoffeeById_callsServiceMethodOnce_whenInvalidId() {
            // Mock the behavior of the coffee service layer to return null when getCoffeeById is called with ID 2L
            when(coffeeService.getCoffeeById(2L)).thenReturn(null);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.getCoffeeById(2L);

            // Verify that the getCoffeeById method in the coffee service layer was called with ID 2L
            verify(coffeeService, times(1)).getCoffeeById(2L);
        }


        @Test
        @DisplayName("Returns response status 200 (OK) when coffee is available")
        void getCoffeeById_returnsHttpStatus200_whenCoffeeIsAvailable() {
            // Create a test coffee object to use as a mock
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object
            when(coffeeService.getCoffeeById(1L)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.getCoffeeById(1L);

            // Assert the HTTP status code is OK
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        @DisplayName("Returns coffee object in response when ID is valid")
        void getCoffeeById_returnsCoffeeObject_whenIdIsValid() {
            // Create a test coffee object to use as a mock
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object
            when(coffeeService.getCoffeeById(1L)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.getCoffeeById(1L);

            // Assert the coffee object in the response body matches the expected coffee object
            assertEquals(coffee, response.getBody());
        }

        @Test
        @DisplayName("Calls service method and returns expected values")
        void getCoffeeById_returnsExpectedValues_whenCoffeeIsAvailable() {
            // Create a test coffee object to use as a mock
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object when getCoffeeById is called with ID 1L
            when(coffeeService.getCoffeeById(1L)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.getCoffeeById(1L);

            // Assert the HTTP status code in the response is OK
            assertEquals(HttpStatus.OK, response.getStatusCode());

            // Assert that the coffee object in the response body matches the expected coffee object
            assertEquals(coffee, response.getBody());

            // Verify that the getCoffeeById method in the coffee service layer was called exactly once with ID 1L
            verify(coffeeService, times(1)).getCoffeeById(1L);
        }


        @Test
        @DisplayName("Returns not found when ID is invalid")
        void getCoffeeById_returnsHttpStatusNotFound_whenInvalidId() {
            // Mock the behavior of the coffee service layer to return null when getCoffeeById is called with ID 2L
            when(coffeeService.getCoffeeById(2L)).thenReturn(null);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.getCoffeeById(2L);

            // Assert the HTTP status code in the response is NOT_FOUND
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("createCoffee()")
    class CreateCoffeeTest {

        @Test
        @DisplayName("Calls service method once")
        void createCoffee_callsServiceMethodOnce() {
            // Create a test coffee object
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object when createCoffee is called with the coffee object
            when(coffeeService.createCoffee(coffee)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.createCoffee(coffee);

            // Verify that the createCoffee method in the coffee service layer was called with the coffee object
            verify(coffeeService, times(1)).createCoffee(coffee);
        }

        @Test
        @DisplayName("Returns http status 'created' (201) when coffee is created")
        void createCoffee_returnsHttpStatus201_whenCoffeeIsCreated() {
            // Create a test coffee object
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object when createCoffee is called with the coffee object
            when(coffeeService.createCoffee(coffee)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.createCoffee(coffee);

            // Assert the HTTP status code is CREATED
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
        }

        @Test
        @DisplayName("Returns created coffee")
        void createCoffee_returnsCreatedCoffee() {
            // Create a test coffee object
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object when createCoffee is called with the coffee object
            when(coffeeService.createCoffee(coffee)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.createCoffee(coffee);

            // Assert the coffee object in the response body matches the expected coffee object
            assertEquals(coffee, response.getBody());
        }

        @Test
        @DisplayName("Creates coffee and returns correct http status and object")
        void createCoffee_returnsCorrectHttpStatusAndObject() {
            // Create a test coffee object
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object when createCoffee is called with the coffee object
            when(coffeeService.createCoffee(coffee)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.createCoffee(coffee);

            // Verify that the createCoffee method in the coffee service layer was called with the coffee object
            verify(coffeeService).createCoffee(coffee);

            // Assert the HTTP status code is CREATED
            assertEquals(HttpStatus.CREATED, response.getStatusCode());

            // Assert that the coffee object in the response body matches the expected coffee object
            assertEquals(coffee, response.getBody());
        }


        @Test
        @DisplayName("Returns created coffee with the same properties")
        void createCoffee_testCreateCoffeeSameProperties() {
            // Create a test coffee object with desired properties
            var coffee = TestObjectFactory.createCoffee();

            // Mock the behavior of the coffee service layer to return the test coffee object when createCoffee is called with the coffee object
            when(coffeeService.createCoffee(coffee)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.createCoffee(coffee);

            // Assert the coffee object in the response body has the same properties as the original coffee object
            assertEquals(coffee.getId(), response.getBody().getId());
            assertEquals(coffee.getName(), response.getBody().getName());
            assertEquals(coffee.getDescription(), response.getBody().getDescription());
            assertEquals(coffee.getPrice(), response.getBody().getPrice());
            assertEquals(coffee.getOrigin(), response.getBody().getOrigin());
            assertEquals(coffee.getRoastLevel(), response.getBody().getRoastLevel());
            assertEquals(coffee.getFlavorNotes(), response.getBody().getFlavorNotes());
            assertEquals(coffee.getCaffeineContent(), response.getBody().getCaffeineContent());

            // Verify that the createCoffee method in the coffee service layer was called with the coffee object
            verify(coffeeService).createCoffee(coffee);
        }
    }

    @Nested
    @DisplayName("updateCoffee()")
    class UpdateCoffeeTest {

        @Test
        @DisplayName("Calls service method once")
        void updateCoffee_callsServiceMethodOnceWithCorrectArg() {
            // Create a test coffee object
            var coffee = new Coffee();

            // Call the method under test
            coffeeController.updateCoffee(1L, coffee);

            // Verify that the updateCoffee method in the coffee service layer was called exactly once with the specified arguments
            verify(coffeeService, times(1)).updateCoffee(1L, coffee);
        }

        @Test
        @DisplayName("Returns http status ok (200) when update-data us valid")
        void updateCoffee_returnsHttpStatus200_whenUpdateDataIsValid() {
            // Create a test coffee object
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object when updateCoffee is called with ID 1L and the coffee object
            when(coffeeService.updateCoffee(1L, coffee)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.updateCoffee(1L, coffee);

            // Assert the HTTP status code is OK
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        @DisplayName("Returns the updated coffee when update-data is valid")
        void updateCoffee_returnsUpdatedCoffee_whenUpdateDataIsValid() {
            // Create a test coffee object
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object when updateCoffee is called with ID 1L and the coffee object
            when(coffeeService.updateCoffee(1L, coffee)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.updateCoffee(1L, coffee);

            // Assert the coffee object in the response body matches the expected coffee object
            assertEquals(coffee, response.getBody());
        }


        @Test
        @DisplayName("Returns correct http status and updated coffee when update-data is valid")
        void updateCoffee_returnsCorrectHttpStatusAndValues_whenUpdateDataIsValid() {
            // Create a test coffee object
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return the test coffee object when updateCoffee is called with ID 1L and the coffee object
            when(coffeeService.updateCoffee(1L, coffee)).thenReturn(coffee);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.updateCoffee(1L, coffee);

            // Verify that the updateCoffee method in the coffee service layer was called with ID 1L and the coffee object
            verify(coffeeService).updateCoffee(1L, coffee);

            // Assert the HTTP status code in the response is OK
            assertEquals(HttpStatus.OK, response.getStatusCode());

            // Assert that the coffee object in the response body matches the expected coffee object
            assertEquals(coffee, response.getBody());
        }


        @Test
        @DisplayName("Returns 'not found' (404) when ID is invalid")
        void updateCoffee_testUpdateCoffeeInvalidId() {
            // Create a test coffee object
            var coffee = new Coffee();

            // Mock the behavior of the coffee service layer to return null when updateCoffee is called with ID 2L and the coffee object
            when(coffeeService.updateCoffee(2L, coffee)).thenReturn(null);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.updateCoffee(2L, coffee);

            // Assert the HTTP status code in the response is NOT_FOUND
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }

    @Nested
    @DisplayName("deleteCoffee()")
    class DeleteCoffee {

        @Test
        @DisplayName("Calls service method once")
        void testServiceMethodInvocation() {
            // Call the method under test
            coffeeController.deleteCoffee(1L);

            // Verify that the deleteCoffee method in the coffee service layer was called exactly once with ID 1L
            verify(coffeeService, times(1)).deleteCoffee(1L);
        }

        @Test
        @DisplayName("Returns 'no content' (204) when coffee is deleted")
        void testDeleteCoffeeValidId() {
            // Mock the behavior of the coffee service layer to return true when deleteCoffee is called with ID 1L
            when(coffeeService.deleteCoffee(1L)).thenReturn(true);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.deleteCoffee(1L);

            // Assert the HTTP status code in the response is NO_CONTENT
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        }

        @Test
        @DisplayName("Returns 'not found' (404) when no coffee was found")
        void testDeleteCoffeeInvalidId() {
            // Mock the behavior of the coffee service layer to return false when deleteCoffee is called with ID 2L
            when(coffeeService.deleteCoffee(2L)).thenReturn(false);

            // Call the method under test and assign the returned value to a response variable
            var response = coffeeController.deleteCoffee(2L);

            // Assert the HTTP status code in the response is NOT_FOUND
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }
}