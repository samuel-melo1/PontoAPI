package com.eletronico.pontoapi.core.position.exceptions;

import com.eletronico.pontoapi.core.position.enums.PositionExceptionStatusError;
import com.eletronico.pontoapi.core.sector.enums.SectionExceptionStatusError;
import org.springframework.http.HttpStatus;

public class PositionAlredyExistException extends RuntimeException{
    private HttpStatus status;
    public PositionAlredyExistException(PositionExceptionStatusError error){
        super(error.getMessage());
        this.status = error.getStatus();
    }

}
