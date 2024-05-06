package com.eletronico.pontoapi.adapters.response.standardResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> responseCreated(String message, HttpStatus httpStatus){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", httpStatus);

        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> responseDelete(String message, HttpStatus httpStatus){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", httpStatus);

        return new ResponseEntity<>(response, httpStatus);
    }

}
