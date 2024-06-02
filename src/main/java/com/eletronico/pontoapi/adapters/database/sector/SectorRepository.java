package com.eletronico.pontoapi.adapters.database.sector;

import com.eletronico.pontoapi.core.sector.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Integer> {

    Optional<Sector> findByName(String name);
}
