package com.eletronico.pontoapi.core.position.services;

import com.eletronico.pontoapi.adapters.database.position.PositionRepository;
import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.position.dto.PositionDTO;
import com.eletronico.pontoapi.core.position.exceptions.PositionAlredyExistException;
import com.eletronico.pontoapi.adapters.utils.mapper.MapperDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.eletronico.pontoapi.core.position.enums.PositionExceptionStatusError.ALREDY_EXIST;


@Service
@Slf4j
public class PositionService {
    private static final Logger LOG = LoggerFactory.getLogger(PositionService.class.getName());

    @Autowired
    private PositionRepository repository;
    public PositionDTO create(PositionDTO dto) {
        LOG.info("creating positions");
        var entity = repository.findById(dto.getCode());
        if (entity.isPresent()) {
            throw new PositionAlredyExistException(ALREDY_EXIST);
        }
        Position newPosition = Position.builder()
                .name(dto.getName()).build();
        return MapperDTO.parseObject(repository.save(newPosition), PositionDTO.class);
    }
    public List<Position> findAll() {
        LOG.info("Finding all people!");
        return repository.findAll();
    }
}
