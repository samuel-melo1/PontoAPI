package com.eletronico.pontoapi.core.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum UserExceptionStatusError {

    ALREDY_EXIST("000", "User alredy Exist. Please, try other!", CONFLICT),
    NOT_EXIST("001","User Not Found!", NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;

}
