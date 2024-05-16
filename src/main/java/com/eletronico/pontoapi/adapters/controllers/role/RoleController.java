package com.eletronico.pontoapi.adapters.controllers.role;

import com.eletronico.pontoapi.adapters.response.standardResponse.ResponseHandler;
import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.role.domain.Role;
import com.eletronico.pontoapi.core.role.dto.RoleDTO;
import com.eletronico.pontoapi.core.role.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
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
