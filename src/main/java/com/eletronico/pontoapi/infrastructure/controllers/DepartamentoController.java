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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
    @Autowired
    private DepartamentoService service;
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid List<Departamento> departamentos) {
        service.create(departamentos);
        return ResponseHandler.responseCreated("Created Sucess", HttpStatus.OK);
    }

    @PostMapping("/disable/{id}")
    public ResponseEntity<Object> disable(@PathVariable("id") Integer id) {
        service.disable(id);
        return ResponseHandler.responseDelete("Disable Departamento Sucess", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<DepartamentoDTO>> list(@RequestParam(name = "page") int page,
                                                      @RequestParam(name = "size") int size){
        return ResponseEntity.ok(service.list(page, size));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DepartamentoDTO>> findByID(@PathVariable("id") Integer id){
        return  ResponseEntity.ok(service.findSectorById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseHandler.responseDelete("Delete Sucess", HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid DepartamentoDTO dto, @PathVariable("id") Integer id) {
        return ResponseHandler.responseUpdate(service.update(dto, id), HttpStatus.OK);
    }
}
