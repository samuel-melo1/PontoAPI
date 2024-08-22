package com.eletronico.pontoapi.infrastructure.controllers;

import com.eletronico.pontoapi.application.usecases.AuthService;
import com.eletronico.pontoapi.entrypoint.dto.request.AuthenticationDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/api/auth/v2/login")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationDTO data){
        if(checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        var token = service.signin(data);
        if(token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return token;
    }

    private boolean checkIfParamsIsNotNull(AuthenticationDTO data) {
        return data == null || data.email() == null || data.email().isBlank()
                || data.email() == null || data.email().isBlank();
    }
}
