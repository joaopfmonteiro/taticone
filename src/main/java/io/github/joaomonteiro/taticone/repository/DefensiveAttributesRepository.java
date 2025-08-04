package io.github.joaomonteiro.taticone.repository;

import io.github.joaomonteiro.taticone.entity.DefensiveAttributes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefensiveAttributesRepository extends JpaRepository<DefensiveAttributes, Long> {
}
