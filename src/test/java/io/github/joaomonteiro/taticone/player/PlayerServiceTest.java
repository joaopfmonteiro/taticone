package io.github.joaomonteiro.taticone.player;

import io.github.joaomonteiro.taticone.dto.player.CreatePlayerRequest;
import io.github.joaomonteiro.taticone.dto.player.PlayerResponse;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import io.github.joaomonteiro.taticone.service.PlayerProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerProfileService playerProfileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar um jogador com sucesso")
    void shouldCreatePlayerSuccessfully() {

        CreatePlayerRequest request = new CreatePlayerRequest("David", 22, "Portugal");

        PlayerProfile savedPlayer = PlayerProfile.builder()
                .id(1L)
                .name("David")
                .age(22)
                .nationality("Portugal")
                .build();

        when(playerRepository.save(any(PlayerProfile.class))).thenReturn(savedPlayer);

        PlayerResponse response = playerProfileService.createPlayer(request);

        assertNotNull(response);
        assertEquals("David", response.name());
        assertEquals(22, response.age());
        assertEquals("Portugal", response.nationality());
        verify(playerRepository, times(1)).save(any(PlayerProfile.class));
    }
}
