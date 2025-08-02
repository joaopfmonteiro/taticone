package io.github.joaomonteiro.taticone.dto.player;

public record PhysicalDataRequest(
        double weight,
        double height,
        int strength,
        int speed,
        int endurance,
        int agility
) {
}
