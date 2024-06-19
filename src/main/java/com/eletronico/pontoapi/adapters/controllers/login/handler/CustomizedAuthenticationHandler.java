package com.eletronico.pontoapi.adapters.controllers.login.handler;

import com.eletronico.pontoapi.core.login.exceptions.InvalidJwtAuthenticationException;
import com.eletronico.pontoapi.adapters.utils.standarderror.RestErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class CustomizedAuthenticationHandler {
    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundExistHandler(InvalidJwtAuthenticationException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), HttpStatus.FORBIDDEN.value(),exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
}
