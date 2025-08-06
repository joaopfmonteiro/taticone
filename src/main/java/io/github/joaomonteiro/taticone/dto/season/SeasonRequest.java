package io.github.joaomonteiro.taticone.dto.season;

import java.time.LocalDate;

public record SeasonRequest(
        LocalDate dateBegin,
        LocalDate dateEnd
) {
}
