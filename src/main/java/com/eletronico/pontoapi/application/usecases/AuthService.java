package com.eletronico.pontoapi.application.usecases;

import com.eletronico.pontoapi.config.tokenConfig.TokenAccessUser;
import com.eletronico.pontoapi.config.tokenConfig.TokenVO;
import com.eletronico.pontoapi.entrypoint.dto.request.AuthenticationDTO;
import com.eletronico.pontoapi.infrastructure.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TokenAccessUser filter;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UserRepository repository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AuthenticationDTO data) {
        try {
            var username = data.email();
            var password = data.password();
            manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findUserByEmail(username);
            var tokenResponse = new TokenVO();
            if (user.isPresent()) {
                tokenResponse = filter.createAccessToken(username, user.get().getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {

        var user = repository.findByEmail(username);
        var tokenResponse = new TokenVO();
        if (user.isPresent()) {
            tokenResponse = filter.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);

    }

}
