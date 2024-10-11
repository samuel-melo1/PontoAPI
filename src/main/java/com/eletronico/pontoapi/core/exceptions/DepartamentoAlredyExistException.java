package com.eletronico.pontoapi.core.exceptions;

import com.eletronico.pontoapi.core.enums.DepartamentoExceptionStatusError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DepartamentoAlredyExistException extends RuntimeException {

    private final HttpStatus status;
    public DepartamentoAlredyExistException(DepartamentoExceptionStatusError error){
        super(error.getMessage());
        this.status = error.getStatus();
    }
}

