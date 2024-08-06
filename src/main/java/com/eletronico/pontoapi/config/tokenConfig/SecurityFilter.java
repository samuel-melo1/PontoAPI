package com.eletronico.pontoapi.config.tokenConfig;

import com.eletronico.pontoapi.infrastructure.persistence.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenAccessUser tokenService;
    private UserRepository repository;
    SecurityFilter(TokenAccessUser tokenService, UserRepository repository){
        this.tokenService = tokenService;
        this.repository = repository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = tokenService.recoverToken((HttpServletRequest) request);
        if(token != null && tokenService.validate(token)){
            Authentication auth = tokenService.getAuthentication(token);
            if(auth != null){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request,response);
    }

}
