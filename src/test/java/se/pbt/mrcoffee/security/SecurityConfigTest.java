package se.pbt.mrcoffee.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void adminAccessRoot() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/address"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"GUEST"})
    public void userCannotAccessRoot() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/address"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    public void adminAccessOtherEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/coffee"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"GUEST"})
    public void userAccessOtherEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/coffee"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void unauthenticatedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/coffee"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}


