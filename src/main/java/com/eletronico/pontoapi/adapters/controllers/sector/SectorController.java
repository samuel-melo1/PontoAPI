package com.eletronico.pontoapi.adapters.controllers.sector;

import com.eletronico.pontoapi.adapters.response.standardResponse.ResponseHandler;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.sector.dto.SectorDTO;
import com.eletronico.pontoapi.core.sector.services.SectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/list-sectors")
    public ResponseEntity<List<Sector>> list(){
        return ResponseEntity.ok(service.list());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseHandler.responseDelete("Delete Sucess", HttpStatus.OK);
    }
}
