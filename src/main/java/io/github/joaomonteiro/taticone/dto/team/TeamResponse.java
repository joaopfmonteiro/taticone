package io.github.joaomonteiro.taticone.dto.team;

import io.github.joaomonteiro.taticone.dto.club.ClubResponse;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;

import java.util.List;

public record TeamResponse(
        Long id,
        String category,
        List<PlayerProfile> playerProfiles,
        ClubResponse clubResponse
) {
}
