package se.pbt.dinoauction.integrationtest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.pbt.dinoauction.testobject.TestObjectFactory;
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
import se.pbt.dinoauction.repository.auctionitem.DinosaurRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("SecurityConfig:")
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    DinosaurRepository dinosaurRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Nested
    @WithMockUser(roles = {"PARTICIPANT"})
    class ParticipantAccessTest {

        @Test
        @DisplayName("GET /dinosaurs - Participant Role Access - Returns 403 Forbidden")
        public void customer_callingGetCoffee_returnStatusOk() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/dinosaurs"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }


        @Test
        @DisplayName("POST /dinosaurs - Participant Role Access - Returns 403 Forbidden")
        public void customer_callingPostCoffee_returnStatusForbidden() throws Exception {
            // Create an object and convert to Json to serve as request body
            var coffee = TestObjectFactory.dinosaur();
            var coffeeJson  = objectMapper.writeValueAsString(coffee);

            mockMvc.perform(MockMvcRequestBuilders.post("/dinosaurs")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(coffeeJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }

        @Test
        @DisplayName("PUT /dinosaurs - Participant Role Access - Returns 403 Forbidden")
        public void customer_callingPutCoffee_returnStatusForbidden() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.put("/dinosaurs")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("1"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }
    }

    @Nested
    @WithMockUser(username = "admin", password = "admin" ,roles = {"ADMIN"})
    class AdminAccessTest {

        @Test
        @DisplayName("GET /dinosaurs - Admin Role Access - Returns 200 OK")
        public void admin_callingGetCoffee_returnStatusOk() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/dinosaurs"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("POST /dinosaurs - Admin Role Access - Returns 201 Created")
        public void admin_callingPostCoffee_returnStatusCreated() throws Exception {
            // Create an object and convert to Json to serve as request body
            var coffee = TestObjectFactory.dinosaur();
            var coffeeJson  = objectMapper.writeValueAsString(coffee);
            mockMvc.perform(MockMvcRequestBuilders.post("/dinosaurs")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(coffeeJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        }


        @Test
        @DisplayName("PUT /dinosaurs - Admin Role Access - Returns 200 Ok")
        public void admin_callingPutCoffee_returnStatusOk() throws Exception {
            var storedCoffee = TestObjectFactory.dinosaur();
            dinosaurRepository.save(storedCoffee);

            var coffeeJson  = objectMapper.writeValueAsString(storedCoffee);

            mockMvc.perform(MockMvcRequestBuilders.put("/dinosaurs/" + storedCoffee.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(coffeeJson))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        @DisplayName("DELETE /dinosaurs - Admin Role Access - Returns 204 Deleted")
        public void admin_callingDeleteCoffee_returnStatusOk() throws Exception {
            var storedCoffee = TestObjectFactory.dinosaur();
            dinosaurRepository.save(storedCoffee);

            mockMvc.perform(MockMvcRequestBuilders.delete("/dinosaurs/" + storedCoffee.getId()))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is(204));
        }
    }

    @Nested
    class AnonymousAccessTest {


        @Test
        @DisplayName("GET /dinosaurs - Anonymous Access - Expect Redirect to Login")
        public void return_redirectToLogin_anonymousAccessGetDinosaurs() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/dinosaurs"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("PUT /dinosaurs - Anonymous Access - Expect Redirect to Login")
        public void return_redirectToLogin_anonymousAccessPutDinosaurs() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.put("/dinosaurs"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }

        @Test
        @DisplayName("POST /dinosaurs - Anonymous Access - Expect Redirect to Login")
        public void return_redirectToLogin_anonymousAccessPostDinosaurs() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/dinosaurs"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
        }


        @Test
        @DisplayName("DELETE /dinosaurs - Anonymous Access - Expect Redirect to Login")
        public void return_redirectToLogin_anonymousAccessDeleteDinosaurs() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/dinosaurs"))
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


