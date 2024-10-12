package com.eletronico.pontoapi.core.exceptions;

import org.springframework.http.HttpStatus;

public interface IDomainErrorCode {
    String getMessage();
    HttpStatus getStatus();
}
