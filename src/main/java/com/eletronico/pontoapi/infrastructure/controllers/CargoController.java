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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargos")
public class CargoController {
    @Autowired
    private CargoService service;
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid CargoDTO cargo) {
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(cargo.getId_cargo()).toUri();
        return ResponseEntity.created(uri).body(service.create(cargo));
    }
    @PostMapping("/disable/{id}")
    public ResponseEntity<Object> disable(@PathVariable("id") Integer id) {
        service.disable(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<Page<CargoDTO>> list(@RequestParam(name = "page") int page,
                                               @RequestParam(name = "size") int size) {
        return ResponseEntity.ok().body(service.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CargoDTO>> findByID(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
