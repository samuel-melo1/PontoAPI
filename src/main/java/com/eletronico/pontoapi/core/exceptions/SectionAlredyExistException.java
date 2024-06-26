package com.eletronico.pontoapi.core.exceptions;

import com.eletronico.pontoapi.core.enums.SectionExceptionStatusError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SectionAlredyExistException extends RuntimeException {

    private final HttpStatus status;
    public SectionAlredyExistException(SectionExceptionStatusError error){
        super(error.getMessage());
        this.status = error.getStatus();
    }
}

