package com.eletronico.pontoapi.core.role.exceptions;

import com.eletronico.pontoapi.core.role.enums.RoleExceptionStatusError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RoleAlredyExistException extends  RuntimeException{

    private final HttpStatus status;
    public RoleAlredyExistException(RoleExceptionStatusError error){
        super(error.getMessage());
        this.status = error.getStatus();
    }
}
