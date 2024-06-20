package com.eletronico.pontoapi.core.user.dto;

import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.role.domain.Role;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.user.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.MapsId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {
    @MapsId("id_user")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @NotBlank(message = "O email não deve ser nulo ou vazio")
    @Email(message = "email deve ser valido")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @NotBlank(message = "A senha não deve ser nula ou vazia")
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @NotBlank(message = "O telefone não deve ser nulo ou vazio")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String telefone;
    @NotBlank(message = "O cpf não deve ser nulo ou vazio")
    @Size(min = 11, max = 11, message = "O cpf deve ter 11 digitos")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cpf;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "O nome não deve ser nulo ou vazio")
    private Boolean status;
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
