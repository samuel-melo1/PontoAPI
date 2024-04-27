package com.eletronico.pontoapi.adapters.config.security;

import com.eletronico.pontoapi.adapters.config.tokenConfig.SecurityFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private SecurityFilter securityFilter;
    public SecurityConfig(SecurityFilter securityFilter){
        this.securityFilter = securityFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/createuser").hasAuthority("GESTOR")
                        .requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/users").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/auth/forgot-password").permitAll()
                        .requestMatchers(AUTH_WHITELIST).hasRole("ADMINISTRADOR")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui.html",
            "/swagger-ui/**"

    };
}
