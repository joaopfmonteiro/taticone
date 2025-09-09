package io.github.joaomonteiro.taticone.team;

import io.github.joaomonteiro.taticone.dto.team.TeamRequest;
import io.github.joaomonteiro.taticone.entity.Club;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.entity.Team;
import io.github.joaomonteiro.taticone.repository.ClubRepository;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
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
    private PlayerRepository playerRepository;
    private TeamService teamService;

    @BeforeEach
    void setUp(){
        teamRepository = mock(TeamRepository.class);
        clubRepository = mock(ClubRepository.class);
        playerRepository = mock(PlayerRepository.class);
        teamService = new TeamService(teamRepository, clubRepository, playerRepository);
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

    @Test
    @DisplayName("Should add a player to a team")
    void shouldAddPlayer(){
        var teamId = 1L;
        var team = new Team();
        team.setId(teamId);

        var playerId = 1L;
        var player = new PlayerProfile();
        player.setId(playerId);

        team.setPlayerProfile(List.of(player));

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));

        teamService.addPlayer(playerId, teamId);

        assertEquals(1, team.getPlayerProfile().size());

    }

}
