package com.eletronico.pontoapi.core.user.dto;

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
    private UserRole userRole;
}
