package com.eletronico.pontoapi.infrastructure.exceptions;

import com.eletronico.pontoapi.core.exceptions.*;
import com.eletronico.pontoapi.infrastructure.gateways.RestErrorMessage;
import com.eletronico.pontoapi.infrastructure.gateways.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectAlreadyExistException.class)
    private ResponseEntity<RestErrorMessage> objectAlreadyExistException(ObjectAlreadyExistException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(err);
    }
    @ExceptionHandler(DataIntegrityException.class)
    private ResponseEntity<RestErrorMessage> dataIntegrityException(DataIntegrityException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getHttpStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getHttpStatus().value()).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    private ResponseEntity<RestErrorMessage> objectNotFoundException(ObjectNotFoundException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus().value()).body(err);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    private ResponseEntity<RestErrorMessage> invalidJwtAuthenticationException(InvalidJwtAuthenticationException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getStatus().value(),exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RestErrorMessage> handleInvalidCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        return new ResponseEntity<RestErrorMessage>(
                new RestErrorMessage(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Email ou senha inválidos!",
                        request.getRequestURI()), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotPermitDeleteAdmException.class)
    private ResponseEntity<RestErrorMessage> notPermitDeleteException(NotPermitDeleteAdmException exception, HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getHttpStatus().value(), exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getHttpStatus().value()).body(err);
    }
    @ExceptionHandler(NotPermitDisableAdmException.class)
    private ResponseEntity<RestErrorMessage> notPermitDisableAdmException(NotPermitDisableAdmException exception,
                                                                          HttpServletRequest request) {
        RestErrorMessage err = new RestErrorMessage(Instant.now(), exception.getHttpStatus().value(),
                exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getHttpStatus().value()).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestErrorMessage> validationCpfException(ConstraintViolationException exception,
                                                                HttpServletRequest request){
        ValidationError errors = new ValidationError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                "Validation Error",request.getRequestURI());

        for(ConstraintViolation field : exception.getConstraintViolations()){
            errors.addError(field.getPropertyPath().toString(), field.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
