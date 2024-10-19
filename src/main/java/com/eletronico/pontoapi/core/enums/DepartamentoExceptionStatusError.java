package com.eletronico.pontoapi.core.enums;

import com.eletronico.pontoapi.core.exceptions.IDomainErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum DepartamentoExceptionStatusError implements IDomainErrorCode {

    ALREDY_EXIST("000", "Departamento já existe. É necessário a criação de outro!", CONFLICT),
    NOT_FOUND_SECTOR("001","Departamento não encontrado!", NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;

    @Override
    public String getMessage() {
        return this.message;
    }


    @Override
    public HttpStatus getStatus() {
        return this.status;
    }
}
