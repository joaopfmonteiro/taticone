package io.github.joaomonteiro.taticone.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import io.github.joaomonteiro.taticone.dto.player.PsychologicalDataRequest;
import jakarta.transaction.Transactional;
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
public class PsychologicalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("Post /api/player/psychological-data/{id} should create and add psycological data to a player")
    void shouldCreateAndAddPsychologicalDataToAPlayer() throws Exception{
        var request = new CreatePlayerRequest("David", 22, "Portugal");

        var playerResponse = mockMvc.perform(post("/api/player")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        var content = playerResponse.getResponse().getContentAsString();
        var createId = objectMapper.readTree(content).get("id").asLong();

        var dataRequest = new PsychologicalDataRequest(
                10,
                10,
                10,
                10,
                10,
                10
        );

        mockMvc.perform(post("/api/player/psychological-data/" + createId)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dataRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.motivation").value(10))
                .andExpect(jsonPath("$.confidence").value(10))
                .andExpect(jsonPath("$.resilience").value(10))
                .andExpect(jsonPath("$.teamSpirit").value(10))
                .andExpect(jsonPath("$.discipline").value(10))
                .andExpect(jsonPath("$.pressureManagement").value(10));
    }
}
