package io.github.joaomonteiro.taticone.repository;

import io.github.joaomonteiro.taticone.entity.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerProfile, Long> {

}
