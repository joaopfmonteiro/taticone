package io.github.joaomonteiro.taticone.controller;

import io.github.joaomonteiro.taticone.dto.player.OffensiveAttributesRequest;
import io.github.joaomonteiro.taticone.dto.player.OffensiveAttributesResponse;
import io.github.joaomonteiro.taticone.service.OffensiveAttributesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/player/offensive-attributes/")
@RequiredArgsConstructor
public class OffensiveAttributesController {

    private final OffensiveAttributesService offensiveAttributesService;

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("{playerId}")
    public ResponseEntity<OffensiveAttributesResponse> create(@PathVariable long playerId, @Valid @RequestBody OffensiveAttributesRequest request){
        OffensiveAttributesResponse offensiveAttributes = offensiveAttributesService.addOffensiveAttributes(playerId,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(offensiveAttributes);
    }
}
