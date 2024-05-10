package com.eletronico.pontoapi.core.user.exceptions;

import com.eletronico.pontoapi.core.user.enums.UserExceptionStatusError;
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
