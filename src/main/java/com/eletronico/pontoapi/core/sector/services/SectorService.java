package com.eletronico.pontoapi.core.sector.services;


import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.sector.dto.SectorDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SectorService {

    SectorDTO create(List<Sector> sectors);
    SectorDTO update(SectorDTO dto);

    Page<SectorDTO> list(Integer page, Integer pageSize);
    void delete(Integer id);
    void disableSector(Integer id_user);

}
