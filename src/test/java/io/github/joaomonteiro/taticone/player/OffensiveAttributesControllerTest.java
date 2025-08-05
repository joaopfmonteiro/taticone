package io.github.joaomonteiro.taticone.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import io.github.joaomonteiro.taticone.dto.player.OffensiveAttributesRequest;
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
public class OffensiveAttributesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("Post /api/player/defensive-attributes/{id} should create and add offensive attributes to a player")
    void shouldCreateAndAddOffensiveAttributesToAPlayer() throws Exception{

        var request = new CreatePlayerRequest("David", 22, "Portugal");
        var playerResponse = mockMvc.perform(post("/api/player")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();
        var content = playerResponse.getResponse().getContentAsString();
        var createdId = objectMapper.readTree(content).get("id").asLong();

        var dataRequest = new OffensiveAttributesRequest(
                10,
                10,
                10,
                10,
                10,
                10,
                10,
                10,
                10);

        mockMvc.perform(post("/api/player/offensive-attributes/" + createdId)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dataRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.stickHandling").value(10))
                .andExpect(jsonPath("$.passing").value(10))
                .andExpect(jsonPath("$.shooting").value(10))
                .andExpect(jsonPath("$.speedWithBall").value(10))
                .andExpect(jsonPath("$.gameVision").value(10))
                .andExpect(jsonPath("$.offTheBallMovement").value(10))
                .andExpect(jsonPath("$.playmakingAbility").value(10))
                .andExpect(jsonPath("$.finishing").value(10))
                .andExpect(jsonPath("$.offensiveToDefensiveTransition").value(10));
    }
}
