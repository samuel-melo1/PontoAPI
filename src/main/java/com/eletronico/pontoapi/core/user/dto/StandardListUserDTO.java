package com.eletronico.pontoapi.core.user.dto;

import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.user.enums.UserRole;
import lombok.Data;

import java.io.Serializable;

@Data
public class StandardListUserDTO implements Serializable {
    private Integer id;
    private String email;
    private String telefone;
    private String name;
    private String cpf;
    private Position position;
    private Sector sector;
    private UserRole userRole;
}
