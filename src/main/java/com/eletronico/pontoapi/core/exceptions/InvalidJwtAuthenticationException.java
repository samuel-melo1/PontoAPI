package com.eletronico.pontoapi.core.exceptions;

import com.eletronico.pontoapi.entrypoint.dto.response.InvalidJwtResponseError;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
@Getter
public class InvalidJwtAuthenticationException extends AuthenticationException {

    private HttpStatus status;
    public InvalidJwtAuthenticationException(InvalidJwtResponseError error) {
        super(error.getMessage());
        this.status = error.getStatus();
    }
}
