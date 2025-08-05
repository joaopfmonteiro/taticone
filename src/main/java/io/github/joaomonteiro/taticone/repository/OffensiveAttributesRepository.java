package io.github.joaomonteiro.taticone.repository;

import io.github.joaomonteiro.taticone.entity.OffensiveAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffensiveAttributesRepository extends JpaRepository<OffensiveAttributes, Long> {
}
