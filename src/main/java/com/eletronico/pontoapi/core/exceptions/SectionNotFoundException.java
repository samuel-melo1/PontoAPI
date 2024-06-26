package com.eletronico.pontoapi.core.exceptions;

import com.eletronico.pontoapi.core.enums.SectionExceptionStatusError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SectionNotFoundException extends RuntimeException{
    private final HttpStatus status;
    public SectionNotFoundException(SectionExceptionStatusError error){
        super(error.getMessage());
        this.status = error.getStatus();
    }
}
