package com.eletronico.pontoapi.core.dto;

import com.eletronico.pontoapi.core.enums.UserRole;

public record UserDTO(String email, String password, String telefone, String name, UserRole userRole) {
}
