package com.eletronico.pontoapi.adapters.controllers;

import com.eletronico.pontoapi.adapters.config.tokenConfig.TokenAccessUser;
import com.eletronico.pontoapi.core.dto.AuthenticationDTO;
import com.eletronico.pontoapi.core.dto.LoginResponseDTO;
import com.eletronico.pontoapi.core.domain.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenAccessUser token;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var tokenAuth = token.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(tokenAuth));
    }
}
