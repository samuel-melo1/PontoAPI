package com.eletronico.pontoapi.core.dto;

import com.eletronico.pontoapi.core.enums.UserRole;

public record StandardListUserDTO(String email,String telefone, String name, UserRole userRole){}
