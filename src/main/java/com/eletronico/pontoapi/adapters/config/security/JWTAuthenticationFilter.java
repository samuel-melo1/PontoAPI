package com.eletronico.pontoapi.adapters.config.security;

import com.eletronico.pontoapi.adapters.config.tokenConfig.TokenAccessUser;
import com.eletronico.pontoapi.core.login.dto.AuthenticationDTO;
import com.eletronico.pontoapi.core.user.domain.User;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


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

            return  authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (AuthenticationException e){
            throw e;
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User username = ((User) authResult.getPrincipal());
        String token = tokenAccess.generateToken(username);
        response.setContentType("application/json");
        response.setStatus(200);
        response.getWriter().append(jsonSuccessfulAuthentication(token, username.getRoles()));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().append(jsonUnsuccessfulAuthentication());
    }

    private CharSequence jsonUnsuccessfulAuthentication() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return "{"
             //   "\"timestamp\": " + dtf.format(LocalDateTime.now()) + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/api/auth/login\"}";
    }
    private CharSequence jsonSuccessfulAuthentication(String token, List<String> roles) {
        return "{" +
                "\"token\": " + token + ", "
                + "\"roles\": " + roles + "}";
    }
}
