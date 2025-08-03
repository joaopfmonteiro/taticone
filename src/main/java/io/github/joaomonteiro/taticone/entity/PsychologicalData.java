package io.github.joaomonteiro.taticone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PsychologicalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int motivation;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int confidence;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int resilience;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int teamSpirit;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int discipline;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int pressureManagement;

    @OneToOne
    @JoinColumn(name = "player_id")
    private PlayerProfile player;
}
