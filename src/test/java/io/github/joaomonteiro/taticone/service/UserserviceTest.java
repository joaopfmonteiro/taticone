package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.auth.dto.RegisterRequest;
import io.github.joaomonteiro.taticone.auth.dto.RegisterResponse;
import io.github.joaomonteiro.taticone.user.entity.Role;
import io.github.joaomonteiro.taticone.user.entity.User;
import io.github.joaomonteiro.taticone.user.repository.UserRepository;
import io.github.joaomonteiro.taticone.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserserviceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldRegisterNewUserSuccessfully(){
        RegisterRequest request = new RegisterRequest(
                "Joao", "Password123", "joao@email.com", Role.PLAYER);

        when(userRepository.existsByUsername("Joao")).thenReturn(false);

        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        RegisterResponse response = userService.register(request);

        assertEquals("Joao", response.username());
        assertEquals("joao@email.com", response.email());
        assertEquals(Role.PLAYER, response.role());

    }

    @Test void shouldThrowExceptionWhenUsernameExists(){
        RegisterRequest request = new RegisterRequest(
                "Joao", "Password123", "joao@email.com", Role.PLAYER);

        when(userRepository.existsByUsername("Joao")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(request));

        assertEquals("Username already exists", exception.getMessage());
    }

    @Test void  shouldEncryptPassword(){
        RegisterRequest request = new RegisterRequest(
                "Joao", "Password123", "joao@email.com", Role.PLAYER);
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> {
                    User user = invocation.getArgument(0);
                    assertNotEquals("Password123!", user.getPassword());
                    assertTrue(user.getPassword().startsWith("$2a$"));
                    return user;
                });

        userService.register(request);
    }
}
