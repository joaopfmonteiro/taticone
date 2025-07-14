package io.github.joaomonteiro.taticone.auth.dto;

import io.github.joaomonteiro.taticone.user.entity.Role;
import jakarta.validation.constraints.*;

public record RegisterRequest (

    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Username must be ate least 3 characters")
    String username,

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "The password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    String password,

    @Email
    @NotBlank(message = "Email is required")
    String email,

    @NotNull(message = "Role is required")
    Role role
) {}
