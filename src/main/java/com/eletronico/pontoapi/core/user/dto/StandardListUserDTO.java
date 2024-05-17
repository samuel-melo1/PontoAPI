package com.eletronico.pontoapi.core.user.dto;

import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.role.domain.Role;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.user.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StandardListUserDTO implements Serializable {
    private Integer id;
    private String email;
    private String telefone;
    private String name;
    private String cpf;
    private Position position;
    private Sector sector;
    @JsonIgnore
    private UserRole userRole;
    private List<Role> permissions;
}
