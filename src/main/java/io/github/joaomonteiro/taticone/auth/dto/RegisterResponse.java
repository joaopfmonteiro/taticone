package io.github.joaomonteiro.taticone.auth.dto;

import io.github.joaomonteiro.taticone.user.entity.Role;

public record RegisterResponse(
        String username,
        String email,
        Role role
) {
}
