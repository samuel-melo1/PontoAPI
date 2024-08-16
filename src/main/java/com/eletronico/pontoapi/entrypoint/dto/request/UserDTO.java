package com.eletronico.pontoapi.entrypoint.dto.request;

import com.eletronico.pontoapi.core.domain.Position;
import com.eletronico.pontoapi.core.domain.Role;
import com.eletronico.pontoapi.core.domain.Sector;
import com.eletronico.pontoapi.core.enums.UserRole;
import com.eletronico.pontoapi.utils.validation.OnCreate;
import com.eletronico.pontoapi.utils.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @NotBlank(message = "O  e-mail não deve ser nulo ou vazio", groups = {OnCreate.class, OnUpdate.class})
    @Email(message = "E-mail Invalido", groups = {OnCreate.class, OnUpdate.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @NotBlank(message = "A senha não deve ser nula ou vazia", groups = {OnCreate.class})
    private String password;
    @NotBlank(message = "Deve ser informado o Telefone", groups = {OnCreate.class, OnUpdate.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String telefone;
    @NotBlank(message = "O cpf não deve ser nulo ou vazio", groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 11, max = 11, message = "O cpf deve conter 11 digitos", groups = {OnCreate.class, OnUpdate.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cpf;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean status;
    @NotBlank(message = "O nome não deve ser nulo ou vazio", groups = {OnCreate.class, OnUpdate.class})
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    private Position position;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Sector sector;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserRole userRole;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Role> permissions;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long totalElements;

}
