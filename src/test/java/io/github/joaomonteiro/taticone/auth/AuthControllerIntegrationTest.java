package io.github.joaomonteiro.taticone.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.joaomonteiro.taticone.auth.dto.LoginRequest;
import io.github.joaomonteiro.taticone.auth.dto.RegisterRequest;
import io.github.joaomonteiro.taticone.user.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private void registerUser(String username, String password, String email, Role role) throws Exception {
        RegisterRequest request = new RegisterRequest(username, password, email, role);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("POST /api/auth/register - should register successfully and returns 201")
    void shouldRegisterSuccessfully() throws Exception {
        RegisterRequest request = new RegisterRequest(
                "Joao", "Password123!", "joao@email.com", Role.PLAYER
        );

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("Joao"))
                .andExpect(jsonPath("$.email").value("joao@email.com"))
                .andExpect(jsonPath("$.role").value("PLAYER"));
    }

    @Test
    @DisplayName("should not register if user name is blank")
    void shouldNotRegisterIfUserNameIsBlank() throws Exception {
        RegisterRequest request = new RegisterRequest(
                "", "Password123!", "joao@email.com", Role.PLAYER
        );

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("should not register if username have lest then 3 characters")
    void shouldNotRegisterIfUserNameHaveLestThen3Characters() throws Exception {
        RegisterRequest request = new RegisterRequest(
                "jo", "Password123!", "joao@email.com", Role.PLAYER
        );

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/auth/login - returns 200 after registration")
    void shouldLoginSuccessfullyAfterRegistration() throws Exception {
        registerUser("Marta", "Password123!", "marta@email.com", Role.PLAYER);

        LoginRequest loginRequest = new LoginRequest("Marta", "Password123!");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

}
