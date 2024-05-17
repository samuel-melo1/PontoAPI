package com.eletronico.pontoapi.adapters.controllers.user;

import com.eletronico.pontoapi.adapters.response.standardResponse.ResponseHandler;
import com.eletronico.pontoapi.core.user.domain.User;
import com.eletronico.pontoapi.core.user.dto.EditListUserDTO;
import com.eletronico.pontoapi.core.user.dto.StandardListUserDTO;
import com.eletronico.pontoapi.core.user.dto.UserDTO;
import com.eletronico.pontoapi.core.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create-user")
    public ResponseEntity<Object> saveUser(@RequestBody UserDTO userDTO) {
       //  return new ResponseEntity<>(userService.saveUser(userDTO), HttpStatus.CREATED)
        userService.saveUser(userDTO);
        return ResponseHandler.responseCreated("Created sucess", HttpStatus.OK);
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
    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody EditListUserDTO dto) {
        return ResponseHandler.responseUpdate(userService.update(dto), HttpStatus.OK);
    }
}