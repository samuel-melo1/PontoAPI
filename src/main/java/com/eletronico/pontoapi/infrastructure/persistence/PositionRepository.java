package com.eletronico.pontoapi.infrastructure.persistence;

import com.eletronico.pontoapi.core.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer>  {
    Optional<Position> findByName(String name);
}
