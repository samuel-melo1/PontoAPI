package com.eletronico.pontoapi.infrastructure.controllers;

import com.eletronico.pontoapi.infrastructure.gateways.ResponseHandler;
import com.eletronico.pontoapi.core.domain.Position;
import com.eletronico.pontoapi.entrypoint.dto.request.PositionDTO;
import com.eletronico.pontoapi.application.gateways.PositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/position")
public class PositionController {
    @Autowired
    private PositionService service;
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid List<Position> positions) {
        service.create(positions);
        return ResponseHandler.responseCreated("Create Sucess", HttpStatus.CREATED);
    }

    @PostMapping("/disable/{id}")
    public ResponseEntity<Object> disable(@PathVariable("id") Integer id) {
        service.disablePosition(id);
        return ResponseHandler.responseDelete("Disable Position Sucess", HttpStatus.OK);
    }

    @GetMapping("/list-positions")
    public ResponseEntity<Page<PositionDTO>> list(@RequestParam(name = "page") int page,
                                                  @RequestParam(name = "size") int size) {
        return new ResponseEntity<>(service.findAll(page, size), HttpStatus.OK);
    }

    @GetMapping("/list-all-positions")
    public ResponseEntity<List<PositionDTO>> listAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<PositionDTO>> findByID(@PathVariable("id") Integer id){
        return  ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseHandler.responseDelete("Delete Sucess", HttpStatus.OK);
    }
}
