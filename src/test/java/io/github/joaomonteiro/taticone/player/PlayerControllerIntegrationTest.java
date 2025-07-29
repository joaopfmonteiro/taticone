package io.github.joaomonteiro.taticone.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("POST /api/player - Deve criar jogador com sucesso (COACH)")
    void shouldCreatePlayerAsCoach() throws Exception {
        var request = new CreatePlayerRequest("David", 22, "Portugal");

        mockMvc.perform(post("/api/player")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("David"))
                .andExpect(jsonPath("$.age").value(22))
                .andExpect(jsonPath("$.nationality").value("Portugal"))
                .andExpect(jsonPath("$.linkedUser").doesNotExist());
    }

    @Test
    @WithMockUser(username = "player1", roles = {"PLAYER"})
    @DisplayName("POST /api/player - Deve bloquear PLAYER (403 Forbidden)")
    void shouldBlockPlayerFromCreatingPlayer() throws Exception {
        var request = new CreatePlayerRequest("David", 22, "Portugal");

        mockMvc.perform(post("/api/player")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("POST /api/player - Deve bloquear utilizador não autenticado (403 Forbidden)")
    void shouldBlockUnauthenticatedUser() throws Exception {
        var request = new CreatePlayerRequest("David", 22, "Portugal");

        mockMvc.perform(post("/api/player")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("POST /api/player - Deve falhar com 400 se faltar campos obrigatórios")
    void shouldFailIfFieldsMissing() throws Exception {
        var request = new CreatePlayerRequest("", 0, "");

        mockMvc.perform(post("/api/player")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
