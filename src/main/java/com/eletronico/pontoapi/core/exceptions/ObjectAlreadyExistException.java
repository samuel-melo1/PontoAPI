package com.eletronico.pontoapi.core.exceptions;

import com.eletronico.pontoapi.core.enums.CargoExceptionStatusError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ObjectAlreadyExistException extends RuntimeException {

    private final HttpStatus status;
    public ObjectAlreadyExistException(CargoExceptionStatusError error){
        super(error.getMessage());
        this.status = error.getStatus();
    }
}
