package com.eletronico.pontoapi.infrastructure.controllers;

import com.eletronico.pontoapi.application.gateways.CargoService;
import com.eletronico.pontoapi.entrypoint.dto.request.CargoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    @GetMapping
    public ResponseEntity<Page<CargoDTO>> list(@RequestParam(name = "page") int page,
                                               @RequestParam(name = "size") int size) {
        return ResponseEntity.ok().body(service.findAll(page, size));
    }
    @PutMapping("/{id}")

    public ResponseEntity<CargoDTO> update(@Valid @RequestBody CargoDTO dto, @PathVariable("id") Integer id){
        return ResponseEntity.ok().body(service.update(dto, id));
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
