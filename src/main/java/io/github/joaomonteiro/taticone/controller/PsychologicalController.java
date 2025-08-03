package io.github.joaomonteiro.taticone.controller;

import io.github.joaomonteiro.taticone.dto.player.PsychologicalDataRequest;
import io.github.joaomonteiro.taticone.dto.player.PsychologicalDataResponse;
import io.github.joaomonteiro.taticone.service.PsychologicalDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/player/psychological-data")
@RequiredArgsConstructor
public class PsychologicalController {

    private final PsychologicalDataService psychologicalDataService;

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("/{playerId}")
    public ResponseEntity<PsychologicalDataResponse> create(@PathVariable long playerId, @RequestBody @Valid PsychologicalDataRequest dataRequest){
        PsychologicalDataResponse psychologicalDataResponse = psychologicalDataService.addPsychologicalData(playerId,dataRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(psychologicalDataResponse);
    }
}
