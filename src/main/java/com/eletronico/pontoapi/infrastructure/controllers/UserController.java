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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/create")
    public ResponseEntity<Object> saveUser(@Validated(OnCreate.class) @RequestBody @Valid  UserDTO userDTO) {
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId_user()).toUri();
        return ResponseEntity.created(uri).body(service.saveUser(userDTO));
    }
    @PutMapping("/disable/{id}")
    public ResponseEntity<Object> disable(@PathVariable("id") Integer id) {
        service.disableUser(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(service.listUser(page, size));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> findByID(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.findUserById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@Validated(OnUpdate.class) @RequestBody @Valid UserDTO dto, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.update(dto, id));
    }

}
