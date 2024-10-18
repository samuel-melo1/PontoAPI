package com.eletronico.pontoapi.application.gateways;

import com.eletronico.pontoapi.core.domain.Role;
import com.eletronico.pontoapi.entrypoint.dto.request.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    RoleDTO create(RoleDTO dto);
    List<RoleDTO> findAll();
    Optional<RoleDTO> findById(Integer id);
    void delete(Integer id);
}
