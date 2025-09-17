package io.github.joaomonteiro.taticone.controller;

import io.github.joaomonteiro.taticone.auth.controller.AuthController;
import io.github.joaomonteiro.taticone.auth.dto.LoginRequest;
import io.github.joaomonteiro.taticone.auth.dto.LoginResponse;
import io.github.joaomonteiro.taticone.auth.dto.RegisterRequest;
import io.github.joaomonteiro.taticone.auth.dto.RegisterResponse;
import io.github.joaomonteiro.taticone.auth.service.AuthService;
import io.github.joaomonteiro.taticone.user.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    @DisplayName("should login successfully")
    void shouldLoginSuccessfully(){

        LoginRequest request = new LoginRequest("Joao", "Password123!");
        LoginResponse response = new LoginResponse("mock.jwt.token");

        when(authService.login(any(LoginRequest.class))).thenReturn(response);
        ResponseEntity<LoginResponse> result = authController.login(request);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("mock.jwt.token", result.getBody().token());
    }

    @Test
    @DisplayName("should register successfully")
    void shouldRegisterSuccessfully() {

        RegisterRequest request = new RegisterRequest("Joao", "Joao","Monteiro","Password123!", "joao@email.com", Role.PLAYER);
        RegisterResponse response = new RegisterResponse("Joao", "Joao","Monteiro","joao@email.com", Role.PLAYER);

        when(authService.register(any(RegisterRequest.class))).thenReturn(response);

        ResponseEntity<RegisterResponse> result = authController.register(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Joao", result.getBody().username());
        assertEquals("joao@email.com", result.getBody().email());
        assertEquals(Role.PLAYER, result.getBody().role());
    }
}
