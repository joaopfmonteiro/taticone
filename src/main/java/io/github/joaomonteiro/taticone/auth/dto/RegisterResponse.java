package io.github.joaomonteiro.taticone.auth.dto;

import io.github.joaomonteiro.taticone.user.entity.Role;

public record RegisterResponse(
        String username,
        String firstName,
        String lastname,
        String email,
        Role role
) {
}
