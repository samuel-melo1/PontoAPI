package com.eletronico.pontoapi.adapters.database;

import com.eletronico.pontoapi.core.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
}
