package com.eletronico.pontoapi.core.user.exceptions;

import com.eletronico.pontoapi.core.user.enums.UserExceptionStatusError;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
@Getter
@Slf4j
public class NotPermitDisableAdmException  extends RuntimeException{

    private final HttpStatus httpStatus;
    public NotPermitDisableAdmException(UserExceptionStatusError error) {
        super(error.getMessage());
        this.httpStatus = error.getStatus();

    }
}
