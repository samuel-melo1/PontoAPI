package com.eletronico.pontoapi.application.usecases;

import com.eletronico.pontoapi.core.exceptions.DataIntegrityException;
import com.eletronico.pontoapi.infrastructure.persistence.PositionRepository;
import com.eletronico.pontoapi.utils.MapperDTO;
import com.eletronico.pontoapi.core.domain.Position;
import com.eletronico.pontoapi.entrypoint.dto.request.PositionDTO;
import com.eletronico.pontoapi.core.enums.PositionExceptionStatusError;
import com.eletronico.pontoapi.core.exceptions.PositionAlredyExistException;
import com.eletronico.pontoapi.core.exceptions.PositionNotFoundException;
import com.eletronico.pontoapi.application.gateways.PositionService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.eletronico.pontoapi.core.enums.DataIntegrityViolationError.NOT_PERMIT_DELETE;
import static com.eletronico.pontoapi.core.enums.PositionExceptionStatusError.NOT_FOUND_POSITION;

@Service
@Slf4j
public class PositionServiceImpl implements PositionService {

    private static final Logger LOG = LoggerFactory.getLogger(PositionService.class.getName());

    @Autowired
    private PositionRepository repository;
    @Transactional
    @Override
    public PositionDTO create(List<Position> positions) {
        LOG.info("creating sector");
        for (Position position : positions) {
            var entity = repository.findByName(position.getName());
            if (entity.isPresent()) {
                throw new PositionAlredyExistException(PositionExceptionStatusError.ALREDY_EXIST);
            }
            position.setStatus(true);
        }
        return MapperDTO.parseObject(repository.saveAll(positions), PositionDTO.class);
    }
    @Cacheable("listPositions")
    public Page<PositionDTO> findAll(Integer page, Integer pageSize) {
        Pageable pages = PageRequest.of(page, pageSize);
        Page<Position> pagedResult = repository.findAll(pages);

        Page<PositionDTO> pagedDto = pagedResult.map(entity -> {
            return MapperDTO.parseObject(entity, PositionDTO.class);
        });
        return pagedDto;
    }
    @Override
    @Cacheable("listAllPositions")
    public List<PositionDTO> findAll() {
        return MapperDTO.parseListObjects(repository.findAll(), PositionDTO.class);
    }

    @Override
    public Optional<PositionDTO> findById(Integer id) {
        LOG.info("find users by id");
        var entity = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new PositionNotFoundException(NOT_FOUND_POSITION)));

        return Optional.ofNullable(MapperDTO.parseObject(Optional.of(entity.get()), PositionDTO.class));
    }
    @Transactional
    @Override
    public void delete(Integer id) {
        LOG.info("delete position by id");
       var entity = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new PositionNotFoundException(NOT_FOUND_POSITION)));

       try {
           repository.delete(entity.get());
           repository.flush();
       } catch (DataIntegrityViolationException e) {
           throw new DataIntegrityException(NOT_PERMIT_DELETE);
       }

    }
    @Transactional
    @Override
    public PositionDTO update(PositionDTO dto) {
        LOG.info("updating users");

        var entity = repository.findByName(dto.getName())
                .orElseThrow(() -> new PositionNotFoundException(NOT_FOUND_POSITION));

        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        return MapperDTO.parseObject(repository.save(entity), PositionDTO.class);
    }

    @Transactional
    @Override
    public void disablePosition(Integer id_user){
        var entity = repository.findById(id_user)
                .orElseThrow(() -> new PositionNotFoundException(NOT_FOUND_POSITION));

        entity.setStatus(false);
        MapperDTO.parseObject(repository.save(entity), PositionDTO.class);
    }
}
