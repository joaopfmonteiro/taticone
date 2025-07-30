package io.github.joaomonteiro.taticone.controller;

import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import io.github.joaomonteiro.taticone.dto.player.PlayerResponse;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.service.PlayerProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/player")
@RequiredArgsConstructor
public class PlayerProfileController {

    private final PlayerProfileService playerProfileService;

    @PreAuthorize("hasRole('COACH')")
    @PostMapping()
    public ResponseEntity<PlayerResponse> create(@RequestBody @Valid CreatePlayerRequest playerDto){
        PlayerResponse playerResponse = playerProfileService.createPlayer(playerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerResponse);
    }

    @PreAuthorize("hasRole('COACH')")
    @GetMapping
    public ResponseEntity<List<PlayerResponse>> getPlayers(@RequestParam(defaultValue = "age") String sort, @RequestParam(defaultValue = "asc") String direction){
        List<PlayerResponse> players = playerProfileService.getPlayersSortBy(sort, direction);
        return ResponseEntity.ok(players);
    }
}
