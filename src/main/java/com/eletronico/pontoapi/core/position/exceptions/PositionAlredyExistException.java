package com.eletronico.pontoapi.core.position.exceptions;

import com.eletronico.pontoapi.core.position.enums.PositionExceptionStatusError;
import com.eletronico.pontoapi.core.sector.enums.SectionExceptionStatusError;
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
