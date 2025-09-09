package io.github.joaomonteiro.taticone.dto.team;

import io.github.joaomonteiro.taticone.dto.club.ClubResponse;

public record TeamResponse(
        String category,
        ClubResponse clubResponse
) {
}
