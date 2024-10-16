package com.eletronico.pontoapi.core.enums;

import com.eletronico.pontoapi.core.exceptions.IDomainErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
@Getter
@AllArgsConstructor
public enum RoleExceptionStatusError implements IDomainErrorCode {

    ROLE_ALREADY_EXIST("000", "Permissão já existe. É necessário a criação de outra!", CONFLICT),
    NOT_FOUND_ROLE("001","Permissão não encontrada!", NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
