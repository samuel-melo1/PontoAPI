package com.eletronico.pontoapi.application.gateways;


import com.eletronico.pontoapi.core.domain.Departamento;
import com.eletronico.pontoapi.entrypoint.dto.request.DepartamentoDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DepartamentoService {

    DepartamentoDTO create(List<Departamento> departamentos);
    DepartamentoDTO update(DepartamentoDTO dto, Integer name);
    Page<DepartamentoDTO> findAll(Integer page, Integer pageSize);
    void delete(Integer id);
    void disable(Integer id_departamento);
    Optional<DepartamentoDTO> findById(Integer id);
}
