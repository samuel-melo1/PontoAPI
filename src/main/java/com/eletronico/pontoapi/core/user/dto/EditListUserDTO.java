package com.eletronico.pontoapi.core.user.dto;

import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.role.domain.Role;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.user.enums.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class EditListUserDTO {
    private String name;
    private String email;
    private String telefone;
    private String cpf;
    private Position position;
    private Sector sector;
    private List<Role> permissions;

}
