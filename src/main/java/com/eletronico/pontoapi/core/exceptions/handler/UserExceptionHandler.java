package com.eletronico.pontoapi.core.exceptions.handler;

import com.eletronico.pontoapi.core.exceptions.UserAlredyExistException;
import com.eletronico.pontoapi.core.exceptions.standarderror.RestErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.Instant;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlredyExistException.class)
    private ResponseEntity<RestErrorMessage> userAlredyExistHandler(UserAlredyExistException exception, HttpServletRequest request){
        String error = "User alredy Exist. Please, try other!";
        HttpStatus status = HttpStatus.CONFLICT;
        RestErrorMessage err = new RestErrorMessage(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
