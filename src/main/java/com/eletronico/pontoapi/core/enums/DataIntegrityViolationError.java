package com.eletronico.pontoapi.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum DataIntegrityViolationError {

    NOT_PERMIT_DELETE("001", "Não é possível deletar! Cargo possui vínculo com um usuário." , BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;
}