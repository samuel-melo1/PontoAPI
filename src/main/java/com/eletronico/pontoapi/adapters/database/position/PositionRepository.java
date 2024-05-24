package com.eletronico.pontoapi.adapters.database.position;

import com.eletronico.pontoapi.core.position.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Position findByName(String name);
}
