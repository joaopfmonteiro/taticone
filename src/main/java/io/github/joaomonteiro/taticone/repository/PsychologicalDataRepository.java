package io.github.joaomonteiro.taticone.repository;

import io.github.joaomonteiro.taticone.entity.PsychologicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsychologicalDataRepository extends JpaRepository<PsychologicalData, Long> {
}
