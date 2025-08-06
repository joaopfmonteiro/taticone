package io.github.joaomonteiro.taticone.service;

import io.github.joaomonteiro.taticone.dto.season.SeasonRequest;
import io.github.joaomonteiro.taticone.dto.season.SeasonResponse;
import io.github.joaomonteiro.taticone.entity.Season;
import io.github.joaomonteiro.taticone.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepository;

    public SeasonResponse createSeason(SeasonRequest seasonRequest) {
        Season season = new Season();
        season.setDateBegin(seasonRequest.dateBegin());
        season.setDateEnd(seasonRequest.dateEnd());

        Season saved = seasonRepository.save(season);

        return new SeasonResponse(saved.getDateBegin(), saved.getDateEnd());
    }
}
