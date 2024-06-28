package com.eletronico.pontoapi.application.gateways;

import com.eletronico.pontoapi.core.domain.Role;
import com.eletronico.pontoapi.entrypoint.dto.request.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO create(RoleDTO dto);
    List<Role> list();
}
