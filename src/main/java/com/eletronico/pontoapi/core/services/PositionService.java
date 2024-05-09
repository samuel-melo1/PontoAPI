package com.eletronico.pontoapi.core.services;

import com.eletronico.pontoapi.adapters.database.PositionRepository;
import com.eletronico.pontoapi.core.domain.Position;
import com.eletronico.pontoapi.core.dto.PositionDTO;
import com.eletronico.pontoapi.utils.mapper.MapperDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class PositionService {
    private static final Logger LOG = LoggerFactory.getLogger(PositionService.class.getName());
    private PositionRepository repository;
    public PositionService(PositionRepository repository){
        this.repository = repository;
    }
    public List<Position> findAll(){
        LOG.info("Finding all people!");
        return repository.findAll();
    }
}
