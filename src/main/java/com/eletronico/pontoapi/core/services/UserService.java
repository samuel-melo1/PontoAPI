package com.eletronico.pontoapi.core.services;

import com.eletronico.pontoapi.adapters.database.UserRepository;
import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.core.dto.UserDTO;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User saveUser(UserDTO userDTO){

        if(userRepository.existsPessoaByEmail(userDTO.email())){
            throw new RuntimeException();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());

        User newUser = User.builder()
                .email(userDTO.email())
                .password(encryptedPassword)
                .userRole(userDTO.userRole())
                .telefone(userDTO.telefone())
                .status(true)
                .name(userDTO.name()).build();

        return userRepository.save(newUser);
    }

    public List<User> listUser(){
        return userRepository.findAll();
    }
}
