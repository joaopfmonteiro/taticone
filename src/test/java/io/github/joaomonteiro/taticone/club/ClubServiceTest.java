package io.github.joaomonteiro.taticone.club;

import io.github.joaomonteiro.taticone.dto.club.ClubRequest;
import io.github.joaomonteiro.taticone.entity.Club;
import io.github.joaomonteiro.taticone.repository.ClubRepository;
import io.github.joaomonteiro.taticone.service.ClubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClubServiceTest {

    private ClubRepository clubRepository;
    private ClubService clubService;

    @BeforeEach
    void setUp(){
        clubRepository = mock(ClubRepository.class);
        clubService = new ClubService(clubRepository);
    }

    @Test
    @DisplayName("Should create a new team")
    void shouldCreateANewClub(){
        var request = new ClubRequest("bir");
        var club = new Club();
        club.setName("bir");

        when(clubRepository.save(any(Club.class))).thenReturn(club);

        var response = clubService.createClub(request);

        assertEquals("bir", response.name());
    }
}
