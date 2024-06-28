package com.eletronico.pontoapi.application.gateways;

import com.eletronico.pontoapi.core.domain.Position;
import com.eletronico.pontoapi.entrypoint.dto.request.PositionDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PositionService {

    PositionDTO create(List<Position> positions);
    Page<PositionDTO> findAll(Integer page, Integer pageSize);
    List<PositionDTO> findAll();
    void delete(Integer id);
    PositionDTO update(PositionDTO dto);
    void disablePosition(Integer id_user);
    Optional<PositionDTO> findById(Integer id);
}
