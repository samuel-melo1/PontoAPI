package com.eletronico.pontoapi.core.position.services;

import com.eletronico.pontoapi.adapters.database.position.PositionRepository;
import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.position.dto.PositionDTO;
import com.eletronico.pontoapi.core.position.enums.PositionExceptionStatusError;
import com.eletronico.pontoapi.core.position.exceptions.PositionAlredyExistException;
import com.eletronico.pontoapi.adapters.utils.mapper.MapperDTO;
import com.eletronico.pontoapi.core.position.exceptions.PositionNotFoundException;

import com.eletronico.pontoapi.core.user.domain.User;
import com.eletronico.pontoapi.core.user.dto.StandardListUserDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public Page<PositionDTO> findAll(Integer page, Integer pageSize) {
        Pageable pages = PageRequest.of(page, pageSize);
        Page<Position> pagedResult = repository.findAll(pages);

        Page<PositionDTO> pagedDto = pagedResult.map(entity -> {
            return MapperDTO.parseObject(entity, PositionDTO.class);
        });
        return pagedDto;
    }
    @Transactional
    public void delete(Integer id) {
        LOG.info("delete position by id");
        Optional<Position> positonExist = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new PositionNotFoundException(PositionExceptionStatusError.NOT_FOUND_POSITION)));

        repository.delete(positonExist.get());
    }
}
