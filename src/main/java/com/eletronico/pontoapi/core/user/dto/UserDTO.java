package com.eletronico.pontoapi.core.user.dto;

import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.role.domain.Role;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.user.enums.UserRole;
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
    @NotBlank(message = "O email não deve ser nulo ou vazio")
    @Email(message = "email deve ser valido")
    private String email;
    @NotBlank(message = "A senha não deve ser nula ou vazia")
    private String password;
    @NotBlank(message = "O telefone não deve ser nulo ou vazio")
    private String telefone;
    @NotBlank(message = "O cpf não deve ser nulo ou vazio")
    @Size(min = 11, max = 11, message = "O cpf deve ter 11 digitos")
    private String cpf;
    @NotBlank(message = "O nome não deve ser nulo ou vazio")
    private String name;
    private Position position;
    private Sector sector;
    private UserRole userRole;
    private List<Role> role;

}
