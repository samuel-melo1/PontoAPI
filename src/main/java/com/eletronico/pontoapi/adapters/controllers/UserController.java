package com.eletronico.pontoapi.adapters.controllers;

import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.core.dto.UserDTO;
import com.eletronico.pontoapi.core.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apí/v1/")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    public ResponseEntity<User> saveUser(@RequestBody UserDTO userDTO){
        var newUser = new User();
        BeanUtils.copyProperties(userDTO, newUser);
        return new ResponseEntity<>(this.userService.saveUser(newUser), HttpStatus.OK);

    }

}
