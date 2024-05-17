package com.eletronico.pontoapi.adapters.controllers.login;

import com.eletronico.pontoapi.adapters.config.tokenConfig.TokenAccessUser;
import com.eletronico.pontoapi.adapters.database.user.UserRepository;
import com.eletronico.pontoapi.core.login.dto.AuthenticationDTO;
import com.eletronico.pontoapi.core.login.dto.LoginResponseDTO;
import com.eletronico.pontoapi.core.role.domain.Role;
import com.eletronico.pontoapi.core.user.domain.User;
import com.eletronico.pontoapi.core.user.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenAccessUser token;
    @Autowired
    private UserService service;
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class.getName());
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        UserDetails users = repository.findByEmail(data.email());
        var auth = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.password()));
        var tokenAuth = token.generateToken((User) auth.getPrincipal());
        var roles = service.findUserByEmail(data.email());
        return ResponseEntity.ok(new LoginResponseDTO(tokenAuth, roles.get().getRoles()));
    }
}
