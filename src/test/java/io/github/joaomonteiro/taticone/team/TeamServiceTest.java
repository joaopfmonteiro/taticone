package io.github.joaomonteiro.taticone.team;

import io.github.joaomonteiro.taticone.dto.team.TeamRequest;
import io.github.joaomonteiro.taticone.entity.Team;
import io.github.joaomonteiro.taticone.repository.TeamRepository;
import io.github.joaomonteiro.taticone.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeamServiceTest {

    private TeamRepository teamRepository;
    private TeamService teamService;

    @BeforeEach
    void setUp(){
        teamRepository = mock(TeamRepository.class);
        teamService = new TeamService(teamRepository);
    }

    @Test
    @DisplayName("Should create a team")
    void shouldCreateATeam(){
        var request = new TeamRequest("sub 9");
        var team = new Team();
        team.setCategory("sub 9");

        when(teamRepository.save(any(Team.class))).thenReturn(team);

        var response = teamService.createTeam(request);

        assertEquals("sub 9", response.category());
    }
}
