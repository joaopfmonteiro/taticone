package io.github.joaomonteiro.taticone.repository;

import io.github.joaomonteiro.taticone.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
