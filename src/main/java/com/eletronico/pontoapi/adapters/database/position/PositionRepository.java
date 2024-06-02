package com.eletronico.pontoapi.adapters.database.position;

import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Optional<Position> findByName(String name);
}
