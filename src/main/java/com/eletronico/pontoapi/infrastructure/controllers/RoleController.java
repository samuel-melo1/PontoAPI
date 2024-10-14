package com.eletronico.pontoapi.infrastructure.controllers;

import com.eletronico.pontoapi.infrastructure.gateways.ResponseHandler;
import com.eletronico.pontoapi.core.domain.Role;
import com.eletronico.pontoapi.entrypoint.dto.request.RoleDTO;
import com.eletronico.pontoapi.application.gateways.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService service;
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid RoleDTO dto){
        service.create(dto);
        return ResponseHandler.responseCreated("Create Sucess", HttpStatus.CREATED);
    }
    @GetMapping("/list-roles")
    public ResponseEntity<List<Role>> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

}
