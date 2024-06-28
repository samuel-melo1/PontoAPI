package com.eletronico.pontoapi.core.exceptions;

import com.eletronico.pontoapi.core.enums.PositionExceptionStatusError;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class PositionAlredyExistException extends RuntimeException{
    private final HttpStatus status;
    public PositionAlredyExistException(PositionExceptionStatusError error){
        super(error.getMessage());
        this.status = error.getStatus();
    }

}
