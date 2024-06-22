package com.eletronico.pontoapi.adapters.config.security;

import com.eletronico.pontoapi.adapters.config.tokenConfig.SecurityFilter;
import com.eletronico.pontoapi.adapters.config.tokenConfig.TokenAccessUser;
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

    private final SecurityFilter securityFilter;
    private final TokenAccessUser tokenAccessUser;

    public SecurityConfig(SecurityFilter securityFilter, TokenAccessUser tokenAccessUser) {
        this.securityFilter = securityFilter;
        this.tokenAccessUser = tokenAccessUser;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager, tokenAccessUser);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/auth/login");

        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/create-user").hasAnyAuthority("ADMINISTRADOR", "GESTOR")
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/list-users").hasAnyAuthority("ADMINISTRADOR", "GESTOR")
                        .requestMatchers(HttpMethod.POST, "/api/auth/forgot-password").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/user/delete/{id}").hasAnyAuthority("ADMINISTRADOR", "GESTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/sector/delete/{id}").hasAnyAuthority("ADMINISTRADOR", "GESTOR")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/user/update").hasAnyAuthority("ADMINISTRADOR", "GESTOR")
                        .requestMatchers(HttpMethod.GET, "/api/v1/position/list-positions").hasAnyAuthority("ADMINISTRADOR", "GESTOR")
                        .requestMatchers(HttpMethod.POST, "/api/v1/position/create").hasAnyAuthority("ADMINISTRADOR", "GESTOR")
                        .requestMatchers(HttpMethod.POST, "/api/v1/sector/create").hasAnyAuthority("ADMINISTRADOR", "GESTOR")
                        .requestMatchers(HttpMethod.GET, "/api/v1/sector/list-sectors").hasAnyAuthority("ADMINISTRADOR", "GESTOR")
                        .requestMatchers(HttpMethod.POST, "/api/v1/role/create").hasAnyAuthority("ADMINISTRADOR", "GESTOR")
                        .requestMatchers(HttpMethod.GET, "/api/v1/role/list-roles").hasAnyAuthority("ADMINISTRADOR", "GESTOR")
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
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
