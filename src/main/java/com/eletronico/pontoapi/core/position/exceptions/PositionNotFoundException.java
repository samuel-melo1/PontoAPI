package com.eletronico.pontoapi.core.position.exceptions;

import com.eletronico.pontoapi.core.position.enums.PositionExceptionStatusError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PositionNotFoundException extends RuntimeException {

    private final HttpStatus status;
    public PositionNotFoundException(PositionExceptionStatusError error){
        super(error.getMessage());
        this.status = error.getStatus();
    }

}
