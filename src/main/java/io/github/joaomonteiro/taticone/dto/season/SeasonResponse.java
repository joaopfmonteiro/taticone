package io.github.joaomonteiro.taticone.dto.season;

import java.time.LocalDate;

public record SeasonResponse(
        LocalDate dateBegin,
        LocalDate dateEnd
) {
}
