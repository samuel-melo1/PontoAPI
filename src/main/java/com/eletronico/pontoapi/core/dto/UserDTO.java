package com.eletronico.pontoapi.core.dto;

import com.eletronico.pontoapi.core.domain.Position;
import com.eletronico.pontoapi.core.enums.UserRole;
import lombok.*;

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
    private UserRole userRole;

}
