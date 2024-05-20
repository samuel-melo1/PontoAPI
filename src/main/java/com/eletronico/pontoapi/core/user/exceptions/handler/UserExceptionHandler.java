package com.eletronico.pontoapi.core.user.exceptions.handler;

import com.eletronico.pontoapi.core.user.exceptions.UserAlredyExistException;
import com.eletronico.pontoapi.core.user.exceptions.UserNotFoundException;
import com.eletronico.pontoapi.adapters.utils.standarderror.RestErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlredyExistException.class)
    private ResponseEntity<RestErrorMessage> userAlredyExistHandler(UserAlredyExistException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getHttpStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getHttpStatus()).body(err);
    }
    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundExistHandler(UserNotFoundException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(err);
    }

    @Override
    @ResponseStatus
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
      RestErrorMessage err = new RestErrorMessage(Instant.now(),ex.getStatusCode().value(), ex.getMessage(), request.getHeader("Instance"));
      return ResponseEntity.status(ex.getStatusCode().value()).body(err);
    }

}
