package io.github.joaomonteiro.taticone.dto.player;

public record PhysicalDataResponse(
        double weight,
        double height,
        int strength,
        int speed,
        int endurance,
        int agility
) {
}
