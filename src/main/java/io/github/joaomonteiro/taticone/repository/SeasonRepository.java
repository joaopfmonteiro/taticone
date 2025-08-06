package io.github.joaomonteiro.taticone.repository;

import io.github.joaomonteiro.taticone.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
}
