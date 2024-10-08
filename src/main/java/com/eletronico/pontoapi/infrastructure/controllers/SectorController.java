package com.eletronico.pontoapi.infrastructure.controllers;

import com.eletronico.pontoapi.infrastructure.gateways.ResponseHandler;
import com.eletronico.pontoapi.core.domain.Sector;
import com.eletronico.pontoapi.entrypoint.dto.request.SectorDTO;
import com.eletronico.pontoapi.application.gateways.SectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sector")
public class SectorController {
    @Autowired
    private SectorService service;
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid List<Sector> sectors) {
        service.create(sectors);
        return ResponseHandler.responseCreated("Created Sucess", HttpStatus.OK);
    }

    @PostMapping("/disable/{id}")
    public ResponseEntity<Object> disable(@PathVariable("id") Integer id) {
        service.disableSector(id);
        return ResponseHandler.responseDelete("Disable Sector Sucess", HttpStatus.OK);
    }

    @GetMapping("/list-sectors")
    public ResponseEntity<Page<SectorDTO>> list(@RequestParam(name = "page") int page,
                                                @RequestParam(name = "size") int size){
        return ResponseEntity.ok(service.list(page, size));
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<SectorDTO>> findByID(@PathVariable("id") Integer id){
        return  ResponseEntity.ok(service.findSectorById(id));
    }
    @GetMapping("/list-all-sectors")
    public ResponseEntity<List<SectorDTO>> listAll(){
        return new ResponseEntity<>(service.listAll(), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseHandler.responseDelete("Delete Sucess", HttpStatus.OK);
    }
    @PutMapping("/update/{name}")
    public ResponseEntity<Object> update(@RequestBody @Valid SectorDTO dto, @PathVariable("name") String name) {
        return ResponseHandler.responseUpdate(service.update(dto, name), HttpStatus.OK);
    }
}
