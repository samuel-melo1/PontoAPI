package com.eletronico.pontoapi.infrastructure.controllers;

import com.eletronico.pontoapi.infrastructure.gateways.ResponseHandler;
import com.eletronico.pontoapi.entrypoint.dto.request.UserDTO;
import com.eletronico.pontoapi.application.gateways.UserService;
import com.eletronico.pontoapi.utils.validation.OnCreate;
import com.eletronico.pontoapi.utils.validation.OnUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/create-user")
    public ResponseEntity<Object> saveUser(@Validated(OnCreate.class) @RequestBody @Valid  UserDTO userDTO) {
        service.saveUser(userDTO);
        return ResponseHandler.responseCreated("Created sucess", HttpStatus.OK);
    }
    @PutMapping("/disable/{id}")
    public ResponseEntity<Object> disable(@PathVariable("id") Integer id) {
        service.disableUser(id);
        return ResponseHandler.responseDelete("Disable User Sucess", HttpStatus.OK);
    }

    @GetMapping("/list-users")
    public ResponseEntity<List<UserDTO>> getUserList(@RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(service.listUser(page, size), HttpStatus.OK);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<UserDTO>> findByID(@PathVariable("id") Integer id){
        return  ResponseEntity.ok(service.findUserById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseHandler.responseDelete("Delete Sucess", HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@Validated(OnUpdate.class)  @RequestBody @Valid UserDTO dto, @PathVariable("id") Integer id) {
        return ResponseHandler.responseUpdate(service.update(dto, id), HttpStatus.OK);
    }

}
