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
public class OffensiveAttributes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private long stickHandling;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private long passing;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private long shooting;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private long speedWithBall;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private long gameVision;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private long offTheBallMovement;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private long playmakingAbility;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private long finishing;

    @Min(value = 0,message = "the value must be between 0 a 20")
    @Max(value = 20, message = "the value must be between 0 a 20")
    private long offensiveToDefensiveTransition;

    @OneToOne
    @JoinColumn(name = "player_id")
    private PlayerProfile player;

}
