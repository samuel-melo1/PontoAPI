package com.eletronico.pontoapi.core.position.services;

import com.eletronico.pontoapi.adapters.database.position.PositionRepository;
import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.position.dto.PositionDTO;
import com.eletronico.pontoapi.core.position.enums.PositionExceptionStatusError;
import com.eletronico.pontoapi.core.position.exceptions.PositionAlredyExistException;
import com.eletronico.pontoapi.adapters.utils.mapper.MapperDTO;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.sector.dto.SectorDTO;
import com.eletronico.pontoapi.core.sector.enums.SectionExceptionStatusError;
import com.eletronico.pontoapi.core.sector.exceptions.SectionAlredyExistException;
import jakarta.transaction.Transactional;
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
    @Transactional
    public PositionDTO create(List<Position> positions) {
        LOG.info("creating sector");
        for (Position position : positions) {
            var entity = repository.findByName(position.getName());
            if (entity != null) {
                throw new PositionAlredyExistException(PositionExceptionStatusError.ALREDY_EXIST);
            }
        }
        return MapperDTO.parseObject(repository.saveAll(positions), PositionDTO.class);
    }
    public List<Position> findAll() {
        LOG.info("Finding all people!");
        return repository.findAll();
    }
}
