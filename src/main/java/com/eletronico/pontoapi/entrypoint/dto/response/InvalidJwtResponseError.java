package com.eletronico.pontoapi.entrypoint.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.*;
@Getter
@AllArgsConstructor
public enum InvalidJwtResponseError {

    EMAIL_OR_PASSWORD_INVALID("001","Email or Password Invalid!", BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
