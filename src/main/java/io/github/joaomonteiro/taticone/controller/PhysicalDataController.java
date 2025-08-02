package io.github.joaomonteiro.taticone.controller;

import io.github.joaomonteiro.taticone.dto.player.PhysicalDataRequest;
import io.github.joaomonteiro.taticone.dto.player.PhysicalDataResponse;
import io.github.joaomonteiro.taticone.service.PhysicalDataService;
import io.github.joaomonteiro.taticone.service.PlayerProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/player/physical-data")
@RequiredArgsConstructor
public class PhysicalDataController {

    private final PhysicalDataService physicalDataService;

    private final PlayerProfileService playerProfileService;

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("/{playerId}")
    public ResponseEntity<PhysicalDataResponse> create(@PathVariable long playerId, @RequestBody @Valid PhysicalDataRequest request){
        PhysicalDataResponse physicalData = physicalDataService.addPhysicalData(playerId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(physicalData);
    }
}
