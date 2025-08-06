package io.github.joaomonteiro.taticone.season;

import io.github.joaomonteiro.taticone.dto.season.SeasonRequest;
import io.github.joaomonteiro.taticone.entity.Season;
import io.github.joaomonteiro.taticone.repository.SeasonRepository;
import io.github.joaomonteiro.taticone.service.SeasonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SeasonServiceTest {

    private SeasonRepository seasonRepository;
    private SeasonService seasonService;

    @BeforeEach()
    void setUp(){
        seasonRepository = mock(SeasonRepository.class);
        seasonService = new SeasonService(seasonRepository);
    }

    @Test
    @DisplayName("Should create a team")
    void shouldCreateASeason(){

        var request = new SeasonRequest(LocalDate.of(2025, 5, 1), LocalDate.of(2026, 5, 1));
        var season = new Season();
        season.setDateBegin(LocalDate.of(2025, 5, 1));
        season.setDateEnd(LocalDate.of(2026, 5, 1));

        when(seasonRepository.save(any(Season.class))).thenReturn(season);

        var response = seasonService.createSeason(request);

        assertEquals(LocalDate.of(2025, 5, 1), response.dateBegin());
        assertEquals(LocalDate.of(2026, 5, 1), response.dateEnd());
    }
}
