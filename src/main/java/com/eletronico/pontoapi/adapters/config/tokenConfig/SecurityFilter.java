package com.eletronico.pontoapi.adapters.config.tokenConfig;

import com.eletronico.pontoapi.adapters.database.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityFilter {

    private TokenAccessUser tokenAccessUser;

    private UserRepository userRepository;

    SecurityFilter(TokenAccessUser tokenAccessUser, UserRepository userRepository){
        this.tokenAccessUser = tokenAccessUser;
        this.userRepository = userRepository;
    }

    //@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null){
            var login = tokenAccessUser.validateToken(token);
            UserDetails userDetails = userRepository.findByEmail(login);

            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);

    }

    public String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return   null;
        return authHeader.replace("Bearer " , "");

    }
}
