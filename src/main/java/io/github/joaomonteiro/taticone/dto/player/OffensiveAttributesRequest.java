package io.github.joaomonteiro.taticone.dto.player;

public record OffensiveAttributesRequest(
        long stickHandling,
        long passing,
        long shooting,
        long speedWithBall,
        long gameVision,
        long offTheBallMovement,
        long playmakingAbility,
        long finishing,
        long offensiveToDefensiveTransition
) {
}
