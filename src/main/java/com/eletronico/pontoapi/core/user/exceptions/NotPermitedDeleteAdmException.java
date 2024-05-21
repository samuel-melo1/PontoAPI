package com.eletronico.pontoapi.core.user.exceptions;

import com.eletronico.pontoapi.core.user.enums.UserExceptionStatusError;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Slf4j
public class NotPermitedDeleteAdmException extends RuntimeException {
    private final HttpStatus httpStatus;
    public NotPermitedDeleteAdmException(UserExceptionStatusError error) {
        super(error.getMessage());
        this.httpStatus = error.getStatus();

    }
}
