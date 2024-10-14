package com.eletronico.pontoapi.infrastructure.gateways;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends RestErrorMessage{

    private List<FieldMessage> errors = new ArrayList<>();
    public ValidationError() {
    }
    public ValidationError(Instant timestamp, Integer status, String message, String path) {
        super(timestamp, status, message, path);
    }
    public List<FieldMessage> getErrors() {
        return errors;
    }
    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
