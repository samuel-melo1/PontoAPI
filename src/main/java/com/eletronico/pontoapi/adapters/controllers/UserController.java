package com.eletronico.pontoapi.adapters.controllers;

import com.eletronico.pontoapi.adapters.response.standardResponse.ResponseHandler;
import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.core.dto.UserDTO;
import com.eletronico.pontoapi.core.services.UserService;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
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
    public ResponseEntity<Object> saveUser(@RequestBody UserDTO userDTO){
        userService.saveUser(userDTO);
        return ResponseHandler.responseCreated("Created Sucess", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUserList(@RequestParam(name = "page") int page,
                                                  @RequestParam(name = "size") int size){
        return new ResponseEntity<>(userService.listUser(page, size), HttpStatus.OK);
    }
}
