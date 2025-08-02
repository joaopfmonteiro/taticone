package io.github.joaomonteiro.taticone.entity;

import io.github.joaomonteiro.taticone.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    private String nationality;

    @OneToOne(optional = true)
    @JoinColumn(name = "user_id")
    private User linkedUser;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private PhysicalData physicalData;
}
