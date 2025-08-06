package io.github.joaomonteiro.taticone.controller;

import io.github.joaomonteiro.taticone.dto.season.SeasonRequest;
import io.github.joaomonteiro.taticone.dto.season.SeasonResponse;
import io.github.joaomonteiro.taticone.service.SeasonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/season")
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonService seasonService;

    @PreAuthorize("hasRole('COACH')")
    @PostMapping()
    public ResponseEntity<SeasonResponse> createSeason(@Valid @RequestBody SeasonRequest seasonRequest){
        SeasonResponse response = seasonService.createSeason(seasonRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
