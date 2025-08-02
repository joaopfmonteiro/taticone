package io.github.joaomonteiro.taticone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhysicalData {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double weight;

    private double height;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int strength;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int speed;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int endurance;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int agility;

    @OneToOne
    @JoinColumn(name = "player_id")
    private PlayerProfile player;

}
