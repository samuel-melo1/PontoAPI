package com.eletronico.pontoapi.adapters.controllers;

import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.core.dto.UserDTO;
import com.eletronico.pontoapi.core.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/createuser")
    public ResponseEntity<User> saveUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(this.userService.saveUser(userDTO), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUserList(@RequestParam  Integer page,
                                                  @RequestParam Integer pageSize){
        return new ResponseEntity<>(userService.listUser(page, pageSize), HttpStatus.OK);
    }
}
