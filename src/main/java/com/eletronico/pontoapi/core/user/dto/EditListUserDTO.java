package com.eletronico.pontoapi.core.user.dto;

import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.user.enums.UserRole;

public record EditListUserDTO(String name, String email, String telefone, String cpf, Position position, Sector sector, UserRole userRole, String password) {
}
