package com.eletronico.pontoapi.core.exceptions;

import com.eletronico.pontoapi.core.enums.DepartamentoExceptionStatusError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DepartamentoNotFoundException extends RuntimeException{
    private final HttpStatus status;
    public DepartamentoNotFoundException(DepartamentoExceptionStatusError error){
        super(error.getMessage());
        this.status = error.getStatus();
    }
}
