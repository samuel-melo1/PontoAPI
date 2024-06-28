package com.eletronico.pontoapi.core.exceptions.handler;

import com.eletronico.pontoapi.core.exceptions.NotPermitDeleteAdmException;
import com.eletronico.pontoapi.core.exceptions.NotPermitDisableAdmException;
import com.eletronico.pontoapi.core.exceptions.UserAlredyExistException;
import com.eletronico.pontoapi.core.exceptions.UserNotFoundException;
import com.eletronico.pontoapi.infrastructure.gateways.RestErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;

@Slf4j
@ControllerAdvice
public class UserExceptionHandler  {

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
    @ExceptionHandler(NotPermitDeleteAdmException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundExistHandler(NotPermitDeleteAdmException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getHttpStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getHttpStatus().value()).body(err);
    }
    @ExceptionHandler(NotPermitDisableAdmException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundExistHandler(NotPermitDisableAdmException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getHttpStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getHttpStatus().value()).body(err);
    }

}
