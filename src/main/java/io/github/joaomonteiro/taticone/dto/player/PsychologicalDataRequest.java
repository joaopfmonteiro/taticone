package io.github.joaomonteiro.taticone.dto.player;

public record PsychologicalDataRequest(int motivation,
                                       int confidence,
                                       int resilience,
                                       int teamSpirit,
                                       int discipline,
                                       int pressureManagement) {
}
