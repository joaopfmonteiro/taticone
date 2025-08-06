package io.github.joaomonteiro.taticone.controller;

import io.github.joaomonteiro.taticone.dto.club.ClubRequest;
import io.github.joaomonteiro.taticone.dto.club.ClubResponse;
import io.github.joaomonteiro.taticone.entity.Club;
import io.github.joaomonteiro.taticone.service.ClubService;
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
@RequestMapping("api/club")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @PreAuthorize("hasRole('COACH')")
    @PostMapping()
    public ResponseEntity<ClubResponse> createClub(@Valid @RequestBody ClubRequest clubRequest){
        ClubResponse clubResponse = clubService.createClub(clubRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(clubResponse);
    }
}
