package com.eletronico.pontoapi.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ObjectNotFoundException extends RuntimeException{

    private final HttpStatus status;

    public ObjectNotFoundException(IDomainErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
    }
}
