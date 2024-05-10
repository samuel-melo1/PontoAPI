package com.eletronico.pontoapi.adapters.controllers.sector;

import com.eletronico.pontoapi.adapters.response.standardResponse.ResponseHandler;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.sector.dto.SectorDTO;
import com.eletronico.pontoapi.core.sector.services.SectorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sector")
public class SectorController {

    private SectorService service;

    public SectorController(SectorService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid SectorDTO dto) {
        service.create(dto);
        return ResponseHandler.responseCreated("Created Sucess", HttpStatus.OK);
    }
    @GetMapping("/list-sectors")
    public ResponseEntity<List<Sector>> list(){
        return ResponseEntity.ok(service.list());
    }
}
