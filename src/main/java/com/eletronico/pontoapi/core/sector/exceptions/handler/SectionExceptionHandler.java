package com.eletronico.pontoapi.core.sector.exceptions.handler;

import com.eletronico.pontoapi.core.sector.exceptions.SectionAlredyExistException;
import com.eletronico.pontoapi.core.sector.exceptions.SectionNotFoundException;
import com.eletronico.pontoapi.core.user.exceptions.UserAlredyExistException;
import com.eletronico.pontoapi.core.user.exceptions.UserNotFoundException;
import com.eletronico.pontoapi.utils.standarderror.RestErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class SectionExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SectionAlredyExistException.class)
    private ResponseEntity<RestErrorMessage> sectionAlredyExistHandler(SectionAlredyExistException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(err);
    }
    @ExceptionHandler(SectionNotFoundException.class)
    private ResponseEntity<RestErrorMessage> sectionNotFoundExistHandler(SectionNotFoundException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(err);
    }
}
