package com.eletronico.pontoapi.entrypoint.dto.request;

import com.eletronico.pontoapi.core.domain.Cargo;
import com.eletronico.pontoapi.core.domain.Departamento;
import com.eletronico.pontoapi.core.domain.Role;
import com.eletronico.pontoapi.core.enums.UserRole;
import com.eletronico.pontoapi.utils.validation.OnCreate;
import com.eletronico.pontoapi.utils.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    @MapsId("id_user")
    private Integer id;
    @NotBlank(message = "O  e-mail não deve ser nulo ou vazio", groups = {OnCreate.class, OnUpdate.class})
    @Email(message = "E-mail Invalido", groups = {OnCreate.class, OnUpdate.class})
    private String email;
    @NotBlank(message = "A senha não deve ser nula ou vazia", groups = {OnCreate.class})
    private String password;
    @NotBlank(message = "Deve ser informado o Telefone", groups = {OnCreate.class, OnUpdate.class})
    private String telefone;
    @NotBlank(message = "O cpf não deve ser nulo ou vazio", groups = {OnCreate.class, OnUpdate.class})
    private String cpf;
    private Boolean status;
    @NotBlank(message = "O nome não deve ser nulo ou vazio", groups = {OnCreate.class, OnUpdate.class})
    private String name;
    private Cargo cargo;
    private Departamento departamento;
    private UserRole userRole;
    private List<Role> permissions;
    private Long totalElements;

}
