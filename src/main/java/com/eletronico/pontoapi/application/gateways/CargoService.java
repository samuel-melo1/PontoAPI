package com.eletronico.pontoapi.application.gateways;

import com.eletronico.pontoapi.core.domain.Cargo;
import com.eletronico.pontoapi.entrypoint.dto.request.CargoDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CargoService {

    CargoDTO create(List<Cargo> cargos);
    Page<CargoDTO> findAll(Integer page, Integer pageSize);
    void delete(Integer id);
    CargoDTO update(CargoDTO dto);
    void disable(Integer id_user);
    Optional<CargoDTO> findById(Integer id);
}
