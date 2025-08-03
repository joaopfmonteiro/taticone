package io.github.joaomonteiro.taticone.player;

import io.github.joaomonteiro.taticone.dto.player.PsychologicalDataRequest;
 import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.entity.PsychologicalData;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import io.github.joaomonteiro.taticone.repository.PsychologicalDataRepository;
import io.github.joaomonteiro.taticone.service.PsychologicalDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PsychologicalDataServiceTest {

    private PsychologicalDataService service;
    private PsychologicalDataRepository psychologicalDataRepository;
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp(){
        psychologicalDataRepository = mock(PsychologicalDataRepository.class);
        playerRepository = mock(PlayerRepository.class);
        service = new PsychologicalDataService(psychologicalDataRepository, playerRepository);
    }

    @Test
    @DisplayName("Should create and add psychological data to a player and return response")
    void shouldCreateAndAddDataToPlayer(){

        long playerId = 1L;
        var player = new PlayerProfile();
        player.setId(playerId);

        var request = new PsychologicalDataRequest(
                10,
                10,
                10,
                10,
                10,
                10);
        var psychologicalData = new PsychologicalData();
        psychologicalData.setMotivation(10);
        psychologicalData.setConfidence(10);
        psychologicalData.setResilience(10);
        psychologicalData.setTeamSpirit(10);
        psychologicalData.setDiscipline(10);
        psychologicalData.setPressureManagement(10);
        psychologicalData.setPlayer(player);

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
        when(psychologicalDataRepository.save(any(PsychologicalData.class))).thenReturn(psychologicalData);

        var response = service.addPsychologicalData(playerId, request);

        assertEquals(10, response.motivation());
        assertEquals(10, response.confidence());
        assertEquals(10, response.resilience());
        assertEquals(10, response.teamSpirit());
        assertEquals(10, response.discipline());
        assertEquals(10, response.pressureManagement());
        verify(psychologicalDataRepository, times(1)).save(any(PsychologicalData.class));

    }
}
