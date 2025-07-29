package io.github.joaomonteiro.taticone.dto.player;

public record PlayerResponse(
        Long id,
        String name,
        int age,
        String nationality
) {}