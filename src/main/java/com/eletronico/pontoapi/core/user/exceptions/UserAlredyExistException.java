package com.eletronico.pontoapi.core.user.exceptions;

import com.eletronico.pontoapi.core.user.enums.UserExceptionStatusError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserAlredyExistException extends RuntimeException {
    private final HttpStatus httpStatus;

    public UserAlredyExistException(UserExceptionStatusError error) {
        super(error.getMessage());
        this.httpStatus = error.getStatus();

    }
}
