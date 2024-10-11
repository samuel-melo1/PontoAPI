package com.eletronico.pontoapi.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
@Getter
@AllArgsConstructor
public enum DepartamentoExceptionStatusError {

    ALREDY_EXIST("000", "Setor já existe. É necessário a criação de outro!", CONFLICT),
    NOT_FOUND_SECTOR("001","Setor não encontrado!", NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
