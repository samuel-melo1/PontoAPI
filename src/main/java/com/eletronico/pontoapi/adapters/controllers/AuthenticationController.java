package com.eletronico.pontoapi.adapters.controllers;

import com.eletronico.pontoapi.adapters.config.tokenConfig.TokenAccessUser;
import com.eletronico.pontoapi.adapters.database.UserRepository;
import com.eletronico.pontoapi.core.dto.AuthenticationDTO;
import com.eletronico.pontoapi.core.dto.LoginResponseDTO;
import com.eletronico.pontoapi.core.domain.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    private UserRepository repository;

    @Autowired
    private TokenAccessUser token;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        UserDetails users = repository.findByEmail(data.email());
        var auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.password()));
        var tokenAuth = token.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(tokenAuth, users.getAuthorities()));
    }
}
