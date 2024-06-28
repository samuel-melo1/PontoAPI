package com.eletronico.pontoapi.infrastructure.persistence;

import com.eletronico.pontoapi.core.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Integer> {

    Optional<Sector> findByName(String name);
}
