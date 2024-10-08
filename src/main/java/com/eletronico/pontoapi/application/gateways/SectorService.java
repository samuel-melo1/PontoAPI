package com.eletronico.pontoapi.application.gateways;


import com.eletronico.pontoapi.core.domain.Sector;
import com.eletronico.pontoapi.entrypoint.dto.request.SectorDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SectorService {

    SectorDTO create(List<Sector> sectors);
    SectorDTO update(SectorDTO dto, String name);
    Page<SectorDTO> list(Integer page, Integer pageSize);
    List<SectorDTO> listAll();
    void delete(Integer id);
    void disableSector(Integer id_sector);
    Optional<SectorDTO> findSectorById(Integer id);
}
