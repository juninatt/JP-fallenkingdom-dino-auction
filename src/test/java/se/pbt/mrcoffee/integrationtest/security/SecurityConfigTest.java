package se.pbt.mrcoffee.integrationtest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.pbt.mrcoffee.integrationtest.testobject.TestObjectFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import se.pbt.mrcoffee.repository.address.AddressRepository;
import se.pbt.mrcoffee.repository.product.CoffeeRepository;
import se.pbt.mrcoffee.repository.receipt.ReceiptRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("SecurityConfig:")
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    CoffeeRepository coffeeRepository;
    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @Nested
    @DisplayName("Customer Accessing Tests:")
    class customerAccessingTest {

        @Test
        @DisplayName("Customer accessing GET /receipt returns 403 Forbidden")
        @WithMockUser(authorities = {"CUSTOMER"})
        public void customer_callingGetReceipt_returnStatusOk() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/receipt"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }

        @Test
        @DisplayName("Customer accessing GET /address returns 403 Forbidden")
        @WithMockUser(authorities = {"CUSTOMER"})
        public void customer_callingGetAddress_returnStatusOk() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/address"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Customer accessing GET /coffee returns 403 Forbidden")
        @WithMockUser(authorities = {"CUSTOMER"})
        public void customer_callingGetCoffee_returnStatusOk() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/coffee"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Customer accessing POST /receipt returns status 403 Forbidden")
        @WithMockUser(authorities = {"CUSTOMER"})
        public void customer_callingPostReceipt_returnStatusForbidden() throws Exception {
            // Create an object and convert to Json to serve as request body
            var receipt = TestObjectFactory.createReceipt();
            var receiptJson  = objectMapper.writeValueAsString(receipt);

            mockMvc.perform(MockMvcRequestBuilders.post("/receipt")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(receiptJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }

        @Test
        @DisplayName("Customer accessing POST /address returns status 403 Forbidden")
        @WithMockUser(authorities = {"CUSTOMER"})
        public void customer_callingPostAddress_returnStatusForbidden() throws Exception {
            // Create an object and convert to Json to serve as request body
            var address = TestObjectFactory.createAddress();
            var addressJson  = objectMapper.writeValueAsString(address);

            mockMvc.perform(MockMvcRequestBuilders.post("/address")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(addressJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }

        @Test
        @DisplayName("Customer accessing POST /coffee returns status 403 Forbidden")
        @WithMockUser(authorities = {"CUSTOMER"})
        public void customer_callingPostCoffee_returnStatusForbidden() throws Exception {
            // Create an object and convert to Json to serve as request body
            var coffee = TestObjectFactory.createCoffee();
            var coffeeJson  = objectMapper.writeValueAsString(coffee);

            mockMvc.perform(MockMvcRequestBuilders.post("/coffee")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(coffeeJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }

        @Test
        @DisplayName("Customer accessing PUT /coffee returns status 403 Forbidden")
        @WithMockUser(authorities = {"CUSTOMER"})
        public void customer_callingPutCoffee_returnStatusForbidden() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.put("/coffee")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("1"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }

        @Test
        @DisplayName("Customer accessing PUT /address returns status 403 Forbidden")
        @WithMockUser(authorities = {"CUSTOMER"})
        public void customer_callingPutAddress_returnStatusForbidden() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/address")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("1"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }

        @Test
        @DisplayName("Customer accessing PUT /receipt returns status 403 Forbidden")
        @WithMockUser(authorities = {"CUSTOMER"})
        public void customer_callingPutReceipt_returnStatusForbidden() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/receipt")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("1"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }
    }

    @Nested
    @DisplayName("Guest Accessing Tests:")
    class guestAccessingTest {

        @Test
        @DisplayName("Guest accessing GET /address returns status 200 Ok")
        @WithMockUser(authorities = {"GUEST"})
        public void guest_callingGetAddress_returnsStatusOk() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/address"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Guest accessing GET /coffee returns status 200 Ok")
        @WithMockUser(authorities = {"GUEST"})
        public void guest_callingGetCoffee_returnsStatusOk() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/coffee"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Guest accessing GET /receipt returns status 200 Ok")
        @WithMockUser(authorities = {"GUEST"})
        public void guest_callingGetReceipt_returnsStatusOk() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/receipt"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
    }

    @Nested
    @DisplayName("Admin Accessing Test")
    class adminAccessingTest {

        @Test
        @DisplayName("Admin accessing GET /address returns status 200 Ok")
        @WithMockUser(authorities = {"ADMIN"})
        public void admin_callingGetAddress_returnStatusOk() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/address"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Admin accessing GET /coffee returns status 200 Ok")
        @WithMockUser(username = "admin", authorities = {"ADMIN"})
        public void admin_callingGetCoffee_returnStatusOk() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/coffee"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Admin accessing GET /receipt returns status 200 Ok")
        @WithMockUser(username = "admin", authorities = {"ADMIN"})
        public void admin_callingGetReceipt_returnStatusOk() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/receipt"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Admin accessing POST /address returns status 201 Created")
        @WithMockUser(username = "admin", password = "admin" ,roles = {"ADMIN"})
        public void admin_callingPostAddress_returnStatusCreated() throws Exception {
            // Create an object and convert to Json to serve as request body
            var coffee = TestObjectFactory.createAddress();
            var coffeeJson  = objectMapper.writeValueAsString(coffee);
            mockMvc.perform(MockMvcRequestBuilders.post("/address")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(coffeeJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        }

        @Test
        @DisplayName("Admin accessing POST /coffee returns status 201 Created")
        @WithMockUser(username = "admin", password = "admin" ,roles = {"ADMIN"})
        public void admin_callingPostCoffee_returnStatusCreated() throws Exception {
            // Create an object and convert to Json to serve as request body
            var coffee = TestObjectFactory.createCoffee();
            var coffeeJson  = objectMapper.writeValueAsString(coffee);
            mockMvc.perform(MockMvcRequestBuilders.post("/coffee")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(coffeeJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        }

        @Test
        @DisplayName("Admin accessing POST /receipt returns status 201 Created")
        @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
        public void admin_callingPostReceipt_returnStatusCreated() throws Exception {
            // Create an object and convert to Json to serve as request body
            var receipt = TestObjectFactory.createReceipt();
            var receiptJson = objectMapper.writeValueAsString(receipt);

            mockMvc.perform(MockMvcRequestBuilders.post("/receipt")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(receiptJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        }

        @Test
        @DisplayName("Admin accessing PUT /address with matching address returns status 200 Ok")
        @WithMockUser(username = "admin", password = "admin" ,roles = {"ADMIN"})
        public void admin_callingPutAddress_withAddressInDatabase_returnStatusOk() throws Exception {
            var storedAddress = TestObjectFactory.createAddress();
            addressRepository.save(storedAddress);

            var addressJson  = objectMapper.writeValueAsString(storedAddress);

            mockMvc.perform(MockMvcRequestBuilders.put("/address/" + storedAddress.getAddressId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(addressJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("Admin accessing PUT /address with no matching address returns status 404 Not Found")
        @WithMockUser(username = "admin", password = "admin" ,roles = {"ADMIN"})
        public void  admin_callingPutAddress_withNoAddressInDatabase_returnStatusNotFound() throws Exception {
            // Create an object and convert to Json to serve as request body
            var updatedAddress = TestObjectFactory.createAddressResponseDTO(1L);
            var updatedAddressJson  = objectMapper.writeValueAsString(updatedAddress);

            mockMvc.perform(MockMvcRequestBuilders.put("/address/" + updatedAddress.addressId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updatedAddressJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        @DisplayName("Admin accessing PUT /coffee returns status 200 Ok")
        @WithMockUser(username = "admin", password = "admin" ,roles = {"ADMIN"})
        public void admin_callingPutCoffee_returnStatusOk() throws Exception {
            var storedCoffee = TestObjectFactory.createCoffee();
            coffeeRepository.save(storedCoffee);

            var coffeeJson  = objectMapper.writeValueAsString(storedCoffee);

            mockMvc.perform(MockMvcRequestBuilders.put("/coffee/" + storedCoffee.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(coffeeJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }



        @Test
        @DisplayName("Admin accessing DELETE /address returns status 204 Deleted")
        @WithMockUser(username = "admin", password = "admin" ,roles = {"ADMIN"})
        public void admin_callingDeleteAddress_returnStatusOk() throws Exception {
            var storedAddress = TestObjectFactory.createAddress();
            addressRepository.save(storedAddress);
            mockMvc.perform(MockMvcRequestBuilders.delete("/address/" + storedAddress.getAddressId()))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is(204));
        }

        @Test
        @DisplayName("Admin accessing DELETE /coffee returns status 204 Deleted")
        @WithMockUser(username = "admin", password = "admin" ,roles = {"ADMIN"})
        public void admin_callingDeleteCoffee_returnStatusOk() throws Exception {
            var storedCoffee = TestObjectFactory.createCoffee();
            coffeeRepository.save(storedCoffee);

            mockMvc.perform(MockMvcRequestBuilders.delete("/coffee/" + storedCoffee.getId()))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is(204));
        }

        @Test
        @DisplayName("Admin accessing DELETE /receipt returns status 204 Deleted")
        @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
        public void admin_callingDeleteReceipt_returnStatusOk() throws Exception {
            var storedReceipt = TestObjectFactory.createReceipt();
            receiptRepository.save(storedReceipt);
            mockMvc.perform(MockMvcRequestBuilders.delete("/receipt/" + storedReceipt.getReceiptId()))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is(204));
        }
    }

    @Nested
    @DisplayName("Anonymous User Accessing Tests:")
    class anonymousAccessingTest {

        @Test
        @DisplayName("Unauthenticated user accessing GET /address is redirected to login")
        public void unauthenticatedUser_callingGetAddress_getsRedirected() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/address"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("Unauthenticated user accessing GET /receipt is redirected to login")
        public void unauthenticatedUser_callingGetReceipt_getsRedirected() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/receipt"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("Unauthenticated user accessing GET /coffee gets redirected to login")
        public void unauthenticatedUser() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/coffee"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("Unauthenticated user accessing PUT /address is redirected to login")
        public void unauthenticatedUser_callingPutAddress_getsRedirected() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.put("/address"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("Unauthenticated user accessing PUT /receipt is redirected to login")
        public void unauthenticatedUser_callingPutReceipt_getsRedirected() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.put("/receipt"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("Unauthenticated user accessing PUT /coffee gets redirected to login")
        public void unauthenticatedUser_callingPutCoffee_getsRedirected() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.put("/coffee"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("Unauthenticated user accessing POST /address is redirected to login")
        public void unauthenticatedUser_callingPostAddress_getsRedirected() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/address"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("Unauthenticated user accessing POST /receipt is redirected to login")
        public void unauthenticatedUser_callingPostReceipt_getsRedirected() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/receipt"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("Unauthenticated user accessing POST /coffee gets redirected to login")
        public void unauthenticatedUser_callingPostCoffee_getsRedirected() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/coffee"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("Unauthenticated user accessing DELETE /address is redirected to login")
        public void unauthenticatedUser_callingDeleteAddress_getsRedirected() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/address"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("Unauthenticated user accessing DELETE /receipt is redirected to login")
        public void unauthenticatedUser_callingDeleteReceipt_getsRedirected() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/receipt"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("Unauthenticated user accessing DELETE /coffee gets redirected to login")
        public void unauthenticatedUser_callingDeleteCoffee_getsRedirected() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/coffee"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }
    }

    @Nested
    class LogInTest {

        // TODO: Add tests for logging in
    }
}


