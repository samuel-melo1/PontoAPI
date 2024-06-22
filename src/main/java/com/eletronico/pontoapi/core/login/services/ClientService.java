package com.eletronico.pontoapi.core.login.services;

import com.eletronico.pontoapi.adapters.database.user.UserRepository;
import com.eletronico.pontoapi.core.login.exceptions.InvalidJwtAuthenticationException;
import com.eletronico.pontoapi.core.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static com.eletronico.pontoapi.core.login.InvalidJwtResponseError.EMAIL_OR_PASSWORD_INVALID;
@Service
@Slf4j
public class ClientService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " Not Found!");
        }
        return user;
    }

    public Optional<User> findByEmail(String email, String password) {
        log.info("find users by email");
        var userExist = Optional.ofNullable(repository.findUserByEmail(email)
                .orElseThrow(() -> new InvalidJwtAuthenticationException(EMAIL_OR_PASSWORD_INVALID)));

        return Optional.of(userExist.get());
    }
}
