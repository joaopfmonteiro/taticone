package io.github.joaomonteiro.taticone.controller;

import io.github.joaomonteiro.taticone.dto.team.TeamRequest;
import io.github.joaomonteiro.taticone.dto.team.TeamResponse;
import io.github.joaomonteiro.taticone.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("{clubId}")
    public ResponseEntity<TeamResponse> createTeam(@PathVariable long clubId, @Valid @RequestBody TeamRequest teamRequest){
        TeamResponse teamResponse = teamService.createTeam(clubId, teamRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(teamResponse);
    }
}
