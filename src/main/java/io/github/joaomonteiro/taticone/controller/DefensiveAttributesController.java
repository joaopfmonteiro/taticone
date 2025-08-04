package io.github.joaomonteiro.taticone.controller;

import io.github.joaomonteiro.taticone.dto.player.DefensiveAttributesRequest;
import io.github.joaomonteiro.taticone.dto.player.DefensiveAttributesResponse;
import io.github.joaomonteiro.taticone.service.DefensiveAttributesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/player/defensive-attributes")
@RequiredArgsConstructor
public class DefensiveAttributesController {

    private final DefensiveAttributesService defensiveAttributesService;

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("{playerId}")
    public ResponseEntity<DefensiveAttributesResponse> create(@PathVariable long playerId, @Valid @RequestBody DefensiveAttributesRequest defensiveAttributesRequest){
        DefensiveAttributesResponse defensiveAttributesResponse = defensiveAttributesService.addDefensiveAttributes(playerId, defensiveAttributesRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(defensiveAttributesResponse);
    }
}
