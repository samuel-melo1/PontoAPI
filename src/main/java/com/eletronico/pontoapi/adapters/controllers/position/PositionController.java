package com.eletronico.pontoapi.adapters.controllers.position;

import com.eletronico.pontoapi.adapters.response.standardResponse.ResponseHandler;
import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.position.dto.PositionDTO;
import com.eletronico.pontoapi.core.position.services.PositionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/list-positions")
    public ResponseEntity<List<Position>> list() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseHandler.responseDelete("Delete Sucess", HttpStatus.OK);
    }
}
