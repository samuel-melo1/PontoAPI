package com.eletronico.pontoapi.core.exceptions.handler;

import com.eletronico.pontoapi.core.exceptions.DataIntegrityException;
import com.eletronico.pontoapi.core.exceptions.PositionAlredyExistException;
import com.eletronico.pontoapi.core.exceptions.PositionNotFoundException;
import com.eletronico.pontoapi.infrastructure.gateways.RestErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class PositionExceptionHandler {
    @ExceptionHandler(PositionAlredyExistException.class)
    private ResponseEntity<RestErrorMessage> positionAlredyExistHandler(PositionAlredyExistException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(err);
    }
    @ExceptionHandler(PositionNotFoundException.class)
    private ResponseEntity<RestErrorMessage> positionNotFoundExistHandler(PositionNotFoundException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    private ResponseEntity<RestErrorMessage> dataIntegrityPosition(DataIntegrityException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getHttpStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getHttpStatus().value()).body(err);
    }
}
