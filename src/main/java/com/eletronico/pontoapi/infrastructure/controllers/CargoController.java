package com.eletronico.pontoapi.infrastructure.controllers;

import com.eletronico.pontoapi.application.gateways.CargoService;
import com.eletronico.pontoapi.infrastructure.gateways.ResponseHandler;
import com.eletronico.pontoapi.core.domain.Cargo;
import com.eletronico.pontoapi.entrypoint.dto.request.CargoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargos")
public class CargoController {
    @Autowired
    private CargoService service;
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid List<Cargo> cargos) {
        service.create(cargos);
        return ResponseHandler.responseCreated("Create Sucess", HttpStatus.CREATED);
    }

    @PostMapping("/disable/{id}")
    public ResponseEntity<Object> disable(@PathVariable("id") Integer id) {
        service.disable(id);
        return ResponseHandler.responseDelete("Disable Cargo Sucess", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CargoDTO>> list(@RequestParam(name = "page") int page,
                                                  @RequestParam(name = "size") int size) {
        return new ResponseEntity<>(service.findAll(page, size), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CargoDTO>> findByID(@PathVariable("id") Integer id){
        return  ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseHandler.responseDelete("Delete Sucess", HttpStatus.OK);
    }
}
