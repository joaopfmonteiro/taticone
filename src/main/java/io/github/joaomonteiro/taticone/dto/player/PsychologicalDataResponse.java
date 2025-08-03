package io.github.joaomonteiro.taticone.dto.player;

public record PsychologicalDataResponse(int motivation,
                                        int confidence,
                                        int resilience,
                                        int teamSpirit,
                                        int discipline,
                                        int pressureManagement
) {
}
