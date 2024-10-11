package com.eletronico.pontoapi.core.exceptions.handler;

import com.eletronico.pontoapi.core.exceptions.DepartamentoAlredyExistException;
import com.eletronico.pontoapi.core.exceptions.DepartamentoNotFoundException;
import com.eletronico.pontoapi.infrastructure.gateways.RestErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.Instant;
@ControllerAdvice
public class DepartamentoExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DepartamentoAlredyExistException.class)
    private ResponseEntity<RestErrorMessage> sectionAlredyExistHandler(DepartamentoAlredyExistException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(err);
    }
    @ExceptionHandler(DepartamentoNotFoundException.class)
    private ResponseEntity<RestErrorMessage> sectionNotFoundExistHandler(DepartamentoNotFoundException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(err);
    }
}
