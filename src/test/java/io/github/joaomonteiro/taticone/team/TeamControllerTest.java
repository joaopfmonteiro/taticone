package io.github.joaomonteiro.taticone.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.joaomonteiro.taticone.dto.club.ClubRequest;
import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import io.github.joaomonteiro.taticone.dto.player.DefensiveAttributesRequest;
import io.github.joaomonteiro.taticone.dto.team.TeamRequest;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
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

    @Test
    @Transactional
    @WithMockUser(username = "coach1",roles = {"COACH"})
    @DisplayName("Post /api/team/add-player should add a player to a team")
    void shouldAddAPlayerToATeam() throws Exception{

        var request = new CreatePlayerRequest("David", 22, "Portugal");
        var playerResponse =  mockMvc.perform(post("/api/player")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        var content = playerResponse.getResponse().getContentAsString();
        var playerId = objectMapper.readTree(content).get("id").asLong();


        var dataRequest = new TeamRequest(
                "Sub 19"
        );

        var clubRequest = new ClubRequest("Bir");
        var clubResponse =  mockMvc.perform(post("/api/club")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(clubRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        var clubContent = clubResponse.getResponse().getContentAsString();
        var clubId = objectMapper.readTree(clubContent).get("id").asLong();

        var teamResponse = mockMvc.perform(post("/api/team/" + clubId)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(dataRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        var teamContent = teamResponse.getResponse().getContentAsString();
        var teamId = objectMapper.readTree(teamContent).get("id").asLong();

        var addPlayerRequest = mockMvc.perform(post("/api/team/add-player/" + playerId +"/"+ teamId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(dataRequest)))
                .andExpect(status().isCreated())
                .andReturn();
    }
}