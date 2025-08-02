package io.github.joaomonteiro.taticone.player;

import io.github.joaomonteiro.taticone.dto.player.PhysicalDataRequest;
import io.github.joaomonteiro.taticone.entity.PhysicalData;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.repository.PhysicalDataRepository;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import io.github.joaomonteiro.taticone.service.PhysicalDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PhysicalDataServiceTest {

    private PhysicalDataService service;
    private PhysicalDataRepository physicalDataRepository;
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        physicalDataRepository = mock(PhysicalDataRepository.class);
        playerRepository = mock(PlayerRepository.class);
        service = new PhysicalDataService(physicalDataRepository, playerRepository);
    }

    @Test
    @DisplayName("Should add physical data to a player and return response")
    void shouldAddPhysicalDataToPlayer() {

        long playerId = 1L;
        var player = new PlayerProfile();
        player.setId(playerId);

        var request = new PhysicalDataRequest(75.5, 1.82, 15, 18, 14, 16);
        var physicalData = new PhysicalData();
        physicalData.setWeight(75.5);
        physicalData.setHeight(1.82);
        physicalData.setStrength(15);
        physicalData.setSpeed(18);
        physicalData.setEndurance(14);
        physicalData.setAgility(16);
        physicalData.setPlayer(player);

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
        when(physicalDataRepository.save(any(PhysicalData.class))).thenReturn(physicalData);

        var response = service.addPhysicalData(playerId, request);

        assertEquals(75.5, response.weight());
        assertEquals(1.82, response.height());
        assertEquals(15, response.strength());
        assertEquals(18, response.speed());
        assertEquals(14, response.endurance());
        assertEquals(16, response.agility());
        verify(physicalDataRepository, times(1)).save(any(PhysicalData.class));
    }

    @Test
    @DisplayName("Should throw exception if player not found")
    void shouldThrowIfPlayerNotFound() {
        long playerId = 99L;
        var request = new PhysicalDataRequest(75.5, 1.82, 15, 18, 14, 16);

        when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.addPhysicalData(playerId, request));
    }
}
