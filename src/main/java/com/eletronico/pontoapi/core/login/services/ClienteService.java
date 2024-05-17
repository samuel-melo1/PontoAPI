package com.eletronico.pontoapi.core.login.services;

import com.eletronico.pontoapi.adapters.database.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var user =  repository.findByEmail(username);
       if (user == null){
            throw new UsernameNotFoundException("Username " + username + " Not Found!");
       }
       return user;
    }
}
