package io.github.joaomonteiro.taticone.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import io.github.joaomonteiro.taticone.dto.player.DefensiveAttributesRequest;
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
public class DefensiveAttributesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("Post /api/player/defensive-attributes/{id} should create and add defensive attributes to a player")
    void shouldCreateAndAddDefensiveAttributesToAPlayer() throws Exception{

        var request = new CreatePlayerRequest("David", 22, "Portugal");
        var playerResponse =  mockMvc.perform(post("/api/player")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        var content = playerResponse.getResponse().getContentAsString();
        var createdId = objectMapper.readTree(content).get("id").asLong();

        var dataRequest = new DefensiveAttributesRequest(
                10,
                10,
                10,
                10,
                10,
                10,
                10,
                10,
                10
        );

        mockMvc.perform(post("/api/player/defensive-attributes/" + createdId)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dataRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.marking").value(10))
                .andExpect(jsonPath("$.tackle").value(10))
                .andExpect(jsonPath("$.defensivePositioning").value(10))
                .andExpect(jsonPath("$.interceptions").value(10))
                .andExpect(jsonPath("$.pressureOnBallCarrier").value(10))
                .andExpect(jsonPath("$.defensiveCoverage").value(10))
                .andExpect(jsonPath("$.shotBlocking").value(10))
                .andExpect(jsonPath("$.ballRecovery").value(10))
                .andExpect(jsonPath("$.defensiveToOffensiveTransition").value(10));
    }
}
