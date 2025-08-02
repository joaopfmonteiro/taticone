package io.github.joaomonteiro.taticone.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import io.github.joaomonteiro.taticone.dto.player.PhysicalDataRequest;
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
public class PhysicalDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @WithMockUser(username = "coach1", roles = {"COACH"})
    @DisplayName("Post /api/player/physical-data/{id} should create and add physical data to a player")
    void shouldCreateAndAddPhysicalDataToAPlayer() throws Exception{
        var request = new CreatePlayerRequest("David", 22, "Portugal");

        var playerResponse = mockMvc.perform(post("/api/player")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        var content = playerResponse.getResponse().getContentAsString();
        var createId = objectMapper.readTree(content).get("id").asLong();

        var dataRequest = new PhysicalDataRequest(
                75.5,
                1.82,
                15,
                18,
                14,
                16);
        mockMvc.perform(post("/api/player/physical-data/" + createId)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dataRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.weight").value(75.5))
                .andExpect(jsonPath("$.height").value(1.82))
                .andExpect(jsonPath("$.strength").value(15))
                .andExpect(jsonPath("$.speed").value(18))
                .andExpect(jsonPath("$.endurance").value(14))
                .andExpect(jsonPath("$.agility").value(16));
    }
}
