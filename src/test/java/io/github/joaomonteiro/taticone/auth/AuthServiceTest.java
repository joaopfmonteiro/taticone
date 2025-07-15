package io.github.joaomonteiro.taticone.auth;


import io.github.joaomonteiro.taticone.auth.dto.LoginRequest;
import io.github.joaomonteiro.taticone.auth.dto.LoginResponse;
import io.github.joaomonteiro.taticone.auth.security.JwtProvider;
import io.github.joaomonteiro.taticone.user.entity.Role;
import io.github.joaomonteiro.taticone.user.entity.User;
import io.github.joaomonteiro.taticone.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtProvider jwtTokenProvider;

    @InjectMocks
    private io.github.joaomonteiro.taticone.auth.service.AuthService authService;

    @Test
    void shouldLoginSuccessfullyWithValidCredentials(){
        LoginRequest request = new LoginRequest("Joao", "Password123!");
        User user = User.builder()
                .username("Joao")
                .password("encodedPassword")
                .email("joao@email.com")
                .role(Role.PLAYER)
                .build();

        when(userService.loadUserByUsername("Joao")).thenReturn(user);
        when(passwordEncoder.matches("Password123!", "encodedPassword")).thenReturn(true);
        when(jwtTokenProvider.generateToken(user)).thenReturn("mock.jwt.token");

        LoginResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals("mock.jwt.token", response.token());
    }
}
