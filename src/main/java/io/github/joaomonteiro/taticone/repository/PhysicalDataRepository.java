package io.github.joaomonteiro.taticone.repository;

import io.github.joaomonteiro.taticone.entity.PhysicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalDataRepository extends JpaRepository<PhysicalData, Long > {
}
