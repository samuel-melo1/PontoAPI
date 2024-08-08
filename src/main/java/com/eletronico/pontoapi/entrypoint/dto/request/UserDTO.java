package com.eletronico.pontoapi.entrypoint.dto.request;

import com.eletronico.pontoapi.core.domain.Position;
import com.eletronico.pontoapi.core.domain.Role;
import com.eletronico.pontoapi.core.domain.Sector;
import com.eletronico.pontoapi.core.enums.UserRole;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @NotBlank(message = "O  e-mail não deve ser nulo ou vazio")
    @Email(message = "E-mail Invalido")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @NotBlank(message = "A senha não deve ser nula ou vazia")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @NotBlank(message = "Deve ser informado o Telefone")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String telefone;
    @NotBlank(message = "O cpf não deve ser nulo ou vazio")
    @Size(min = 11, max = 11, message = "O cpf deve conter 11 digitos")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cpf;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean status;
    @NotBlank(message = "O nome não deve ser nulo ou vazio")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
