package io.github.joaomonteiro.taticone.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.joaomonteiro.taticone.dto.club.ClubRequest;
import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import io.github.joaomonteiro.taticone.dto.player.DefensiveAttributesRequest;
import io.github.joaomonteiro.taticone.dto.team.TeamRequest;
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
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    @WithMockUser(username = "coach1",roles = {"COACH"})
    @DisplayName("Post /api/team should create a team and add to a Club")
    void shouldCreateATeam() throws Exception{

        var request = new ClubRequest("Bir");
        var clubResponse =  mockMvc.perform(post("/api/club")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        var content = clubResponse.getResponse().getContentAsString();
        var createdId = objectMapper.readTree(content).get("id").asLong();

        var dataRequest = new TeamRequest(
               "Sub 19"
        );

        mockMvc.perform(post("/api/team/" + createdId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dataRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.category").value("Sub 19"));
    }
}