package com.eletronico.pontoapi.core.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum UserExceptionStatusError {

    ALREDY_EXIST("000", "User alredy Exist. Please, try other!", CONFLICT),
    NOT_EXIST("001","User Not Found!", NOT_FOUND),
    NOT_PERMITED_DELETE("002", "Administrador cannot be deleted", BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;

}
