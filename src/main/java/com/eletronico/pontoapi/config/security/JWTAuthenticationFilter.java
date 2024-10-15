package com.eletronico.pontoapi.config.security;

import com.eletronico.pontoapi.config.tokenConfig.TokenAccessUser;
import com.eletronico.pontoapi.entrypoint.dto.request.AuthenticationDTO;
import com.eletronico.pontoapi.core.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private TokenAccessUser tokenAccess;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, TokenAccessUser tokenAccess) {
        super();
        this.authenticationManager = authenticationManager;
        this.tokenAccess = tokenAccess;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AuthenticationDTO credentials = new ObjectMapper().readValue(request.getInputStream(), AuthenticationDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());

            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (AuthenticationException e) {
            throw e;
        }
    }

}
