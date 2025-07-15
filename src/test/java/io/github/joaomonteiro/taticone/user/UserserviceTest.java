package io.github.joaomonteiro.taticone.user;

import io.github.joaomonteiro.taticone.auth.dto.RegisterRequest;
import io.github.joaomonteiro.taticone.auth.dto.RegisterResponse;
import io.github.joaomonteiro.taticone.user.entity.Role;
import io.github.joaomonteiro.taticone.user.entity.User;
import io.github.joaomonteiro.taticone.user.repository.UserRepository;
import io.github.joaomonteiro.taticone.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserserviceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("should register a new User successfully")
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

    @Test
    @DisplayName("should throw exception when username exists")
    void shouldThrowExceptionWhenUsernameExists(){
        RegisterRequest request = new RegisterRequest(
                "Joao", "Password123", "joao@email.com", Role.PLAYER);

        when(userRepository.existsByUsername("Joao")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(request));

        assertEquals("Username already exists", exception.getMessage());
    }

    @Test
    @DisplayName("should encrypt password")
    void  shouldEncryptPassword() {
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
