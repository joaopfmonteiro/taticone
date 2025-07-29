package io.github.joaomonteiro.taticone.auth.service;

import io.github.joaomonteiro.taticone.auth.dto.LoginRequest;
import io.github.joaomonteiro.taticone.auth.dto.LoginResponse;
import io.github.joaomonteiro.taticone.auth.dto.RegisterRequest;
import io.github.joaomonteiro.taticone.auth.dto.RegisterResponse;
import io.github.joaomonteiro.taticone.auth.security.JwtProvider;
import io.github.joaomonteiro.taticone.exception.InvalidCredentialsException;
import io.github.joaomonteiro.taticone.exception.InvalidRoleException;
import io.github.joaomonteiro.taticone.user.entity.Role;
import io.github.joaomonteiro.taticone.user.entity.User;
import io.github.joaomonteiro.taticone.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtTokenProvider;

    public RegisterResponse register(RegisterRequest request) {
        if(request.role() != Role.COACH){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request);
            throw new InvalidRoleException("Only COACH role is allowed for self-registration");
        }
        return userService.register(request);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userService.loadUserByUsername(request.username());

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        String token = jwtTokenProvider.generateToken(user);
        return new LoginResponse(token);
    }
}
