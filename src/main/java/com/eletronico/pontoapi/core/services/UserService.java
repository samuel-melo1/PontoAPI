package com.eletronico.pontoapi.core.services;

import com.eletronico.pontoapi.adapters.database.UserRepository;
import com.eletronico.pontoapi.core.domain.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        List<User> userExist = userRepository.findAllById(Collections.singleton(user.getId_user()));
        if(!userExist.isEmpty()){
            throw new RuntimeException();
        }
        return userRepository.save(user);
    }

    public List<User> listUser(){
        return userRepository.findAll();
    }
}
