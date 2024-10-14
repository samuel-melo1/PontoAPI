package com.eletronico.pontoapi.core.enums;

import com.eletronico.pontoapi.core.exceptions.IDomainErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum UserExceptionStatusError implements IDomainErrorCode {

    ALREDY_EXIST("000", "Usuário já existe. É necessário a criação de outro!", CONFLICT),
    NOT_EXIST("001","Usuário não encontrado", NOT_FOUND),
    NOT_PERMITED_DELETE("002", "Administrador não pode ser deletado!", BAD_REQUEST),
    NOT_PERMITED_DISABLE("003", "Administrador não pode ser desabilitado!", BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;

}
