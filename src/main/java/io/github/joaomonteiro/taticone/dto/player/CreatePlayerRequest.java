package io.github.joaomonteiro.taticone.dto.player;

import jakarta.validation.constraints.NotBlank;

public record CreatePlayerRequest(
        @NotBlank
        String name,
        int age,
        String nationality
){}
