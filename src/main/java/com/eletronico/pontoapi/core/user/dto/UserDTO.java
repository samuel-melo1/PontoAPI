package com.eletronico.pontoapi.core.user.dto;

import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.user.enums.UserRole;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String password;
    private String telefone;
    private String cpf;
    private String name;
    private Position position;
    private Sector sector;
    private UserRole userRole;
    private List<String> roles;

}
