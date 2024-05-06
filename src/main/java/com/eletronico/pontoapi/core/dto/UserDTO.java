package com.eletronico.pontoapi.core.dto;

import com.eletronico.pontoapi.core.domain.Position;
import com.eletronico.pontoapi.core.enums.UserRole;

public record UserDTO(String email, String password, String telefone, String cpf, String name, Position position,  UserRole userRole) {
}
