package com.eletronico.pontoapi.application.usecases;

import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.core.exceptions.DataIntegrityException;
import com.eletronico.pontoapi.core.exceptions.ObjectAlreadyExistException;
import com.eletronico.pontoapi.core.exceptions.ObjectNotFoundException;
import com.eletronico.pontoapi.infrastructure.persistence.CargoRepository;
import com.eletronico.pontoapi.utils.MapperDTO;
import com.eletronico.pontoapi.core.domain.Cargo;
import com.eletronico.pontoapi.entrypoint.dto.request.CargoDTO;
import com.eletronico.pontoapi.application.gateways.CargoService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.eletronico.pontoapi.core.enums.CargoExceptionStatusError.*;
import static com.eletronico.pontoapi.core.enums.DataIntegrityViolationError.NOT_PERMIT_DELETE;

@Service
@Slf4j
public class CargoServiceImpl implements CargoService {

    private static final Logger LOG = LoggerFactory.getLogger(CargoService.class.getName());

    @Autowired
    private CargoRepository repository;

    @Transactional
    @Override
    public CargoDTO create(CargoDTO cargo) {
        LOG.info("creating departamento");

        var entity = repository.findByName(cargo.getName());
        if (entity.isPresent()) {
            throw new ObjectAlreadyExistException(ALREDY_EXIST);
        }
        Cargo newCargo = new Cargo();
        BeanUtils.copyProperties(cargo, newCargo);
        return MapperDTO.parseObject(repository.save(newCargo), CargoDTO.class);
    }

    @Cacheable("listPositions")
    public Page<CargoDTO> findAll(Integer page, Integer pageSize) {
        Pageable pages = PageRequest.of(page, pageSize);
        Page<Cargo> pagedResult = repository.findAll(pages);

        Page<CargoDTO> pagedDto = pagedResult.map(entity -> {
            return MapperDTO.parseObject(entity, CargoDTO.class);
        });
        return pagedDto;
    }

    @Override
    public Optional<CargoDTO> findById(Integer id) {
        LOG.info("find users by id");
        var entity = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(NOT_FOUND_CARGO)));

        return Optional.ofNullable(MapperDTO.parseObject(Optional.of(entity.get()), CargoDTO.class));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        LOG.info("delete cargo by id");
        var entity = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(NOT_FOUND_CARGO)));

        try {
            repository.delete(entity.get());
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(NOT_PERMIT_DELETE);
        }

    }

    @Transactional
    @Override
    public CargoDTO update(CargoDTO dto) {
        LOG.info("updating users");

        var entity = repository.findByName(dto.getName())
                .orElseThrow(() -> new ObjectNotFoundException(NOT_FOUND_CARGO));

        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        return MapperDTO.parseObject(repository.save(entity), CargoDTO.class);
    }

    @Transactional
    @Override
    public void disable(Integer id_user) {
        var entity = repository.findById(id_user)
                .orElseThrow(() -> new ObjectNotFoundException(NOT_FOUND_CARGO));

        entity.setStatus(false);
        MapperDTO.parseObject(repository.save(entity), CargoDTO.class);
    }
}
