package com.eletronico.pontoapi.core.position.services;

import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.position.dto.PositionDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PositionService {

    PositionDTO create(List<Position> positions);
    Page<PositionDTO> findAll(Integer page, Integer pageSize);
    List<PositionDTO> findAll();
    void delete(Integer id);
    PositionDTO update(PositionDTO dto);
    void disablePosition(Integer id_user);

}
