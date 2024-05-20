package com.eletronico.pontoapi.core.user.dto;

import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.role.domain.Role;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.user.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class EditListUserDTO {
    @NotBlank(message = "O nome não deve ser nulo ou vazio")
    private String name;
    @NotBlank(message = "O email não deve ser nulo ou vazio")
    private String email;
    @NotBlank(message = "O telefone não deve ser nulo ou vazio")
    private String telefone;
    @NotBlank(message = "O cpf não deve ser nulo ou vazio")
    @Size(min = 11, max = 11, message = "O cpf deve ter 11 digitos")
    private String cpf;
    private Position position;
    private Sector sector;
    private List<Role> permissions;

}
