package io.github.joaomonteiro.taticone.player;

import io.github.joaomonteiro.taticone.dto.player.OffensiveAttributesRequest;
import io.github.joaomonteiro.taticone.entity.OffensiveAttributes;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.repository.OffensiveAttributesRepository;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import io.github.joaomonteiro.taticone.service.OffensiveAttributesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class OffensiveAttributesServiceTest {

    private OffensiveAttributesService offensiveAttributesService;
    private OffensiveAttributesRepository offensiveAttributesRepository;
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp(){
        offensiveAttributesRepository = mock(OffensiveAttributesRepository.class);
        playerRepository = mock(PlayerRepository.class);
        offensiveAttributesService = new OffensiveAttributesService(offensiveAttributesRepository, playerRepository);
    }

    @Test
    @DisplayName("Should create and add offensive attributes to a player and return response")
    void shouldAddOffensiveAttributes(){

        long playerId = 1L;
        var player = new PlayerProfile();
        player.setId(playerId);

        var request = new OffensiveAttributesRequest(10,10,10,10,
                10,10,10,10,10);
        var offensiveAttributes = new OffensiveAttributes();
        offensiveAttributes.setStickHandling(10);
        offensiveAttributes.setPassing(10);
        offensiveAttributes.setShooting(10);
        offensiveAttributes.setSpeedWithBall(10);
        offensiveAttributes.setGameVision(10);
        offensiveAttributes.setOffTheBallMovement(10);
        offensiveAttributes.setPlaymakingAbility(10);
        offensiveAttributes.setFinishing(10);
        offensiveAttributes.setOffensiveToDefensiveTransition(10);
        offensiveAttributes.setPlayer(player);

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
        when(offensiveAttributesRepository.save(any(OffensiveAttributes.class))).thenReturn(offensiveAttributes);

        var response = offensiveAttributesService.addOffensiveAttributes(playerId, request);

        assertEquals(10, response.stickHandling());
        assertEquals(10, response.passing());
        assertEquals(10, response.shooting());
        assertEquals(10, response.speedWithBall());
        assertEquals(10, response.gameVision());
        assertEquals(10, response.offTheBallMovement());
        assertEquals(10, response.playmakingAbility());
        assertEquals(10, response.finishing());
        assertEquals(10, response.offensiveToDefensiveTransition());
        verify(offensiveAttributesRepository, times(1)).save(any(OffensiveAttributes.class));
    }
}