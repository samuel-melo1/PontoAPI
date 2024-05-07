package com.eletronico.pontoapi.adapters.controllers;

import com.eletronico.pontoapi.core.dto.PositionDTO;
import com.eletronico.pontoapi.core.services.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/position")
public class PositionController {
    private PositionService service;
    public PositionController(PositionService service){
        this.service = service;
    }
    @GetMapping("/list-positions")
    public ResponseEntity<List<PositionDTO>> list(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
