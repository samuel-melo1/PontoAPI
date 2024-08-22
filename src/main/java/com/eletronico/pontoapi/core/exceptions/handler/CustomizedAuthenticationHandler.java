package com.eletronico.pontoapi.core.exceptions.handler;

import com.eletronico.pontoapi.core.exceptions.InvalidJwtAuthenticationException;
import com.eletronico.pontoapi.infrastructure.gateways.RestErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class CustomizedAuthenticationHandler {
    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundExistHandler(InvalidJwtAuthenticationException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getStatus().value(),exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        return new ResponseEntity<RestErrorMessage>(
                new RestErrorMessage(Instant.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                        request.getRequestURI()), HttpStatus.UNAUTHORIZED);
    }
}
