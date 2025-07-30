package io.github.joaomonteiro.taticone.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    @Transactional
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("POST /api/player - Should create player successfully (COACH)")
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
    @Transactional
    @WithMockUser(username = "player1", roles = {"PLAYER"})
    @DisplayName("POST /api/player - Should block PLAYER (403 Forbidden)")
    void shouldBlockPlayerFromCreatingPlayer() throws Exception {
        var request = new CreatePlayerRequest("David", 22, "Portugal");

        mockMvc.perform(post("/api/player")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("POST /api/player - Should block unauthenticated user (403 Forbidden")
    void shouldBlockUnauthenticatedUser() throws Exception {
        var request = new CreatePlayerRequest("David", 22, "Portugal");

        mockMvc.perform(post("/api/player")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("POST /api/player - Should fail with 400 if required fields are missing")
    void shouldFailIfFieldsMissing() throws Exception {
        var request = new CreatePlayerRequest("", 0, "");

        mockMvc.perform(post("/api/player")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("GET Should return players sorted by ascending age when sort=age is passed")
    void shouldReturnPlayersSortByAscendingAge() throws Exception{
        registerPlayer("Joao", 35, "Portuguese");
        registerPlayer("Marta", 36, "Deutsch");
        registerPlayer("Nuno", 29, "Portuguese");

        mockMvc.perform(get("/api/player?sort=age&direction=asc")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(29))
                .andExpect(jsonPath("$[1].age").value(35))
                .andExpect(jsonPath("$[2].age").value(36));
    }

    @Test
    @Transactional
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("GET Should return players sorted by descending age when sort=age is passed")
    void shouldReturnPlayerSortByDescendingAge() throws Exception{
        registerPlayer("Joao", 35, "Portuguese");
        registerPlayer("Marta", 36, "Deutsch");
        registerPlayer("Nuno", 29, "Portuguese");

        mockMvc.perform(get("/api/player?sort=age&direction=desc")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].age").value(36))
                .andExpect(jsonPath("$[1].age").value(35))
                .andExpect(jsonPath("$[2].age").value(29));
    }

    @Test
    @Transactional
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("GET Should return players sorted by ascending age when sort=name is passed")
    void shouldReturnPlayerSortByAscending() throws Exception{
        registerPlayer("Joao", 35, "Portuguese");
        registerPlayer("Marta", 36, "Deutsch");
        registerPlayer("Nuno", 29, "Portuguese");

        mockMvc.perform(get("/api/player?sort=name&direction=asc")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Joao"))
                .andExpect(jsonPath("$[1].name").value("Marta"))
                .andExpect(jsonPath("$[2].name").value("Nuno"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("GET Should return players sorted by descending age when sort=name is passed")
    void shouldReturnPlayerSortByDescending()throws Exception{
        registerPlayer("Joao", 35, "Portuguese");
        registerPlayer("Marta", 36, "Deutsch");
        registerPlayer("Nuno", 29, "Portuguese");

        mockMvc.perform(get("/api/player?sort=name&direction=desc")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Nuno"))
                .andExpect(jsonPath("$[1].name").value("Marta"))
                .andExpect(jsonPath("$[2].name").value("Joao"));
    }

    private void registerPlayer(String name, int age, String nationality) throws Exception  {
        var request = new CreatePlayerRequest(name, age, nationality);

        mockMvc.perform(post("/api/player")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }
}
