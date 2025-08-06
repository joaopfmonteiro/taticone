package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.dto.team.TeamRequest;
import io.github.joaomonteiro.taticone.dto.team.TeamResponse;
import io.github.joaomonteiro.taticone.entity.Team;
import io.github.joaomonteiro.taticone.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamResponse createTeam(TeamRequest request){

        Team team = new Team();
        team.setCategory(request.category());
        Team saved = teamRepository.save(team);

        return new TeamResponse(saved.getCategory());
    }
}
