package com.eletronico.pontoapi.core.exceptions;

import com.eletronico.pontoapi.core.enums.UserExceptionStatusError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends  RuntimeException{
    private HttpStatus status;
    public UserNotFoundException(UserExceptionStatusError error){
        super(error.getMessage());
        this.status = error.getStatus();
    }
}
