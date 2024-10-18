package com.eletronico.pontoapi.infrastructure.controllers;

import com.eletronico.pontoapi.core.domain.Departamento;
import com.eletronico.pontoapi.infrastructure.gateways.ResponseHandler;
import com.eletronico.pontoapi.entrypoint.dto.request.DepartamentoDTO;
import com.eletronico.pontoapi.application.gateways.DepartamentoService;
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
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    @PostMapping("/create")
    public ResponseEntity<DepartamentoDTO> create(@RequestBody @Valid DepartamentoDTO dto) {
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId_departamento()).toUri();
        return ResponseEntity.created(uri).body(service.create(dto));
    }
    @PostMapping("/disable/{id}")
    public ResponseEntity<Object> disable(@PathVariable("id") Integer id) {
        service.disable(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<Page<DepartamentoDTO>> list(@RequestParam(name = "page") int page,
                                                      @RequestParam(name = "size") int size){
        return ResponseEntity.ok(service.findAll(page, size));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DepartamentoDTO>> findByID(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<DepartamentoDTO> update(@RequestBody @Valid DepartamentoDTO dto, @PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(service.update(dto, id));
    }
}
