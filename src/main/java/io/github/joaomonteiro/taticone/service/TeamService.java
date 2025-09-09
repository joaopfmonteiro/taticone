package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.dto.club.ClubResponse;
import io.github.joaomonteiro.taticone.dto.team.TeamRequest;
import io.github.joaomonteiro.taticone.dto.team.TeamResponse;
import io.github.joaomonteiro.taticone.entity.Club;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.entity.Team;
import io.github.joaomonteiro.taticone.repository.ClubRepository;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import io.github.joaomonteiro.taticone.repository.TeamRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;

    public TeamResponse createTeam(long clubId, TeamRequest request){

        Club club = clubRepository.findById(clubId)
                .orElseThrow(()-> new RuntimeException("Club not found"));

        Team team = new Team();
        team.setCategory(request.category());
        team.setClub(club);

        club.setTeam(List.of(team));

        ClubResponse clubResponse = new ClubResponse(
                club.getId(),
                club.getName()
        );

        Team saved = teamRepository.save(team);
        return new TeamResponse(
                saved.getId(),
                saved.getCategory(),
                saved.getPlayerProfile(),
                clubResponse
        );
    }


    @Transactional
    public TeamResponse addPlayer(long playerId, long teamId) {

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        PlayerProfile playerProfile = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        if (team.getPlayerProfile() == null) {
            team.setPlayerProfile(new ArrayList<>());
        }

        boolean alreadyInTeam = team.getPlayerProfile().stream()
                .anyMatch(p -> Objects.equals(p.getId(), playerProfile.getId()));

        if (!alreadyInTeam) {
            team.getPlayerProfile().add(playerProfile);
        }

        Team savedTeam = teamRepository.save(team);

        Club club = savedTeam.getClub();
        ClubResponse clubResponse = (club == null)
                ? null
                : new ClubResponse(
                club.getId(),
                club.getName()
        );

        return new TeamResponse(
                savedTeam.getId(),
                savedTeam.getCategory(),
                savedTeam.getPlayerProfile(),
                clubResponse
        );
    }

}
