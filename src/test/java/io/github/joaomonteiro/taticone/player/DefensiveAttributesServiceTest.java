package io.github.joaomonteiro.taticone.player;

import io.github.joaomonteiro.taticone.dto.player.DefensiveAttributesRequest;
import io.github.joaomonteiro.taticone.entity.DefensiveAttributes;
import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import io.github.joaomonteiro.taticone.repository.DefensiveAttributesRepository;
import io.github.joaomonteiro.taticone.repository.PlayerRepository;
import io.github.joaomonteiro.taticone.service.DefensiveAttributesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DefensiveAttributesServiceTest {

   private DefensiveAttributesService service;
   private DefensiveAttributesRepository defensiveAttributesRepository;
   private PlayerRepository playerRepository;

   @BeforeEach
   void setUp(){
       defensiveAttributesRepository = mock(DefensiveAttributesRepository.class);
       playerRepository = mock(PlayerRepository.class);
       service = new DefensiveAttributesService(defensiveAttributesRepository, playerRepository);
   }

   @Test
   @DisplayName("Should create and add defensive attributes to a player and return response")
   void shouldAddDefensiveAttributes(){

       long playerId = 1L;
       var player = new PlayerProfile();
       player.setId(playerId);

       var request = new DefensiveAttributesRequest(10,10,10,10,10,10,10,10,10);
       var defensiveAttributes = new DefensiveAttributes();
       defensiveAttributes.setMarking(10);
       defensiveAttributes.setTackle(10);
       defensiveAttributes.setDefensivePositioning(10);
       defensiveAttributes.setInterceptions(10);
       defensiveAttributes.setPressureOnBallCarrier(10);
       defensiveAttributes.setDefensiveCoverage(10);
       defensiveAttributes.setShotBlocking(10);
       defensiveAttributes.setBallRecovery(10);
       defensiveAttributes.setDefensiveToOffensiveTransition(10);
       defensiveAttributes.setPlayer(player);

       when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));
       when(defensiveAttributesRepository.save(any(DefensiveAttributes.class))).thenReturn(defensiveAttributes);

       var response = service.addDefensiveAttributes(playerId, request);

       assertEquals(10,response.marking());
       assertEquals(10,response.tackle());
       assertEquals(10,response.defensiveCoverage());
       assertEquals(10,response.interceptions());
       assertEquals(10,response.pressureOnBallCarrier());
       assertEquals(10,response.defensiveCoverage());
       assertEquals(10,response.shotBlocking());
       assertEquals(10,response.ballRecovery());
       assertEquals(10,response.defensivePositioning());
       verify(defensiveAttributesRepository, times(1)).save(any(DefensiveAttributes.class));
   }
}
