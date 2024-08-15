package com.eletronico.pontoapi.core.exceptions;

import com.eletronico.pontoapi.core.enums.DataIntegrityViolationError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DataIntegrityException extends RuntimeException {

    private final HttpStatus httpStatus;

    public DataIntegrityException(DataIntegrityViolationError error){
        super(error.getMessage());
        this.httpStatus = error.getStatus();

    }
}
