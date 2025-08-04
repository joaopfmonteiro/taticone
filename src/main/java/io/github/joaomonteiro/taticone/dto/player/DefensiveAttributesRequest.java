package io.github.joaomonteiro.taticone.dto.player;

public record DefensiveAttributesRequest(
        int marking,
        int tackle,
        int defensivePositioning,
        int interceptions,
        int pressureOnBallCarrier,
        int defensiveCoverage,
        int shotBlocking,
        int ballRecovery,
        int defensiveToOffensiveTransition
) {
}
