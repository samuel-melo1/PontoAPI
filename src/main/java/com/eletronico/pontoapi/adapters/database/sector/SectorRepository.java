package com.eletronico.pontoapi.adapters.database.sector;

import com.eletronico.pontoapi.core.sector.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Integer> {

    Sector findByName(String name);
}
