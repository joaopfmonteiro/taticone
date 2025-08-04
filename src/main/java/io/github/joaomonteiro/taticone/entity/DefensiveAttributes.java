package io.github.joaomonteiro.taticone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefensiveAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int marking;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int tackle;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int defensivePositioning;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int interceptions;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int pressureOnBallCarrier;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int defensiveCoverage;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int shotBlocking;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int ballRecovery;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private int defensiveToOffensiveTransition;

    @OneToOne
    @JoinColumn(name = "player_id")
    private PlayerProfile player;
}
