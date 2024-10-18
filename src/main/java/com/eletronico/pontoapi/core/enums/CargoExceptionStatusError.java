package com.eletronico.pontoapi.core.enums;

import com.eletronico.pontoapi.core.exceptions.IDomainErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@Getter
public enum CargoExceptionStatusError implements IDomainErrorCode {
    ALREDY_EXIST("000", "Cargo já existe. É necessário a criação de outro!", CONFLICT),
    NOT_FOUND_CARGO("001","Cargo não encontrado!", NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }
}
