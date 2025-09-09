package io.github.joaomonteiro.taticone.team;

import io.github.joaomonteiro.taticone.dto.team.TeamRequest;
import io.github.joaomonteiro.taticone.dto.team.TeamResponse;
import io.github.joaomonteiro.taticone.entity.Club;
import io.github.joaomonteiro.taticone.entity.Team;
import io.github.joaomonteiro.taticone.repository.ClubRepository;
import io.github.joaomonteiro.taticone.repository.TeamRepository;
import io.github.joaomonteiro.taticone.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeamServiceTest {

    private TeamRepository teamRepository;
    private ClubRepository clubRepository;
    private TeamService teamService;

    @BeforeEach
    void setUp(){
        teamRepository = mock(TeamRepository.class);
        clubRepository = mock(ClubRepository.class);
        teamService = new TeamService(teamRepository, clubRepository);
    }

    @Test
    @DisplayName("Should create a team and add to a club")
    void shouldCreateATeam(){
        var clubId = 1L;
        var club = new Club();
        club.setId(clubId);

        var request = new TeamRequest("sub 9");
        var team = new Team();
        team.setCategory("sub 9");
        team.setClub(club);

        club.setTeam(List.of(team));

        when(clubRepository.findById(clubId)).thenReturn(Optional.of(club));
        when(teamRepository.save(any(Team.class))).thenReturn(team);

        var response = teamService.createTeam(clubId, request);

        assertEquals("sub 9", response.category());
    }


}
