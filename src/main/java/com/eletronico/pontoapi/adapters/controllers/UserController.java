package com.eletronico.pontoapi.adapters.controllers;

import com.eletronico.pontoapi.adapters.response.standardResponse.ResponseHandler;
import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.core.dto.StandardListUserDTO;
import com.eletronico.pontoapi.core.dto.UserDTO;
import com.eletronico.pontoapi.core.services.UserService;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<Object> saveUser(@RequestBody UserDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseHandler.responseCreated("Created Sucess", HttpStatus.OK);
    }
    @GetMapping("/list-users")
    public ResponseEntity<Page<StandardListUserDTO>> getUserList(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                 @RequestParam(name = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(userService.listUser(page, size), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> saveUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return ResponseHandler.responseDelete("Delete Sucess", HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody UserDTO userDTO) {
        return ResponseHandler.responseUpdate(userService.update(userDTO), HttpStatus.OK);
    }
}
