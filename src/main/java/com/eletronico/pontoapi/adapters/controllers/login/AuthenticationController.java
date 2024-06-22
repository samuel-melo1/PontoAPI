package com.eletronico.pontoapi.adapters.controllers.login;

import com.eletronico.pontoapi.adapters.config.tokenConfig.TokenAccessUser;
import com.eletronico.pontoapi.adapters.database.user.UserRepository;
import com.eletronico.pontoapi.core.login.dto.AuthenticationDTO;
import com.eletronico.pontoapi.core.login.services.ClientService;
import jakarta.validation.Valid;;
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
    private ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data){
        var roles = clientService.findByEmail(data.email(), data.password());
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.email(), data.password()));
        return ResponseEntity.ok().build();
    }
}
