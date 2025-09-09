package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.dto.club.ClubResponse;
import io.github.joaomonteiro.taticone.dto.team.TeamRequest;
import io.github.joaomonteiro.taticone.dto.team.TeamResponse;
import io.github.joaomonteiro.taticone.entity.Club;
import io.github.joaomonteiro.taticone.entity.Team;
import io.github.joaomonteiro.taticone.repository.ClubRepository;
import io.github.joaomonteiro.taticone.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final ClubRepository clubRepository;

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
                saved.getCategory(),
                clubResponse
        );
    }

}
