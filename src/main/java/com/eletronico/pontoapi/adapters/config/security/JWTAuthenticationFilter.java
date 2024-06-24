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
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("roles", username.getRoles());
        responseData.put("token", token);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseData));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        responseData.put("error", "Não autorizado");
        responseData.put("message", "Email ou senha inválidos");
        responseData.put("path", "/api/auth/login");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseData));
    }
}
