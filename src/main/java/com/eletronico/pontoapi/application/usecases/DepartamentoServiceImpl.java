package com.eletronico.pontoapi.application.usecases;

import com.eletronico.pontoapi.core.domain.Departamento;
import com.eletronico.pontoapi.infrastructure.persistence.DepartamentoRepository;
import com.eletronico.pontoapi.utils.MapperDTO;
import com.eletronico.pontoapi.entrypoint.dto.request.DepartamentoDTO;
import com.eletronico.pontoapi.core.exceptions.DepartamentoAlredyExistException;
import com.eletronico.pontoapi.core.exceptions.DepartamentoNotFoundException;
import com.eletronico.pontoapi.application.gateways.DepartamentoService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static com.eletronico.pontoapi.core.enums.DepartamentoExceptionStatusError.ALREDY_EXIST;
import static com.eletronico.pontoapi.core.enums.DepartamentoExceptionStatusError.NOT_FOUND_SECTOR;

@Service
@Slf4j
public class DepartamentoServiceImpl implements DepartamentoService {
    private static final Logger LOG = LoggerFactory.getLogger(DepartamentoServiceImpl.class.getName());

    @Autowired
    private DepartamentoRepository repository;

    @Transactional
    @Override
    public DepartamentoDTO create(List<Departamento> departamentos) {
        LOG.info("creating departamento");
        for (Departamento departamento : departamentos) {
            var entity = repository.findByName(departamento.getName());
            if (entity.isPresent()) {
                throw new DepartamentoAlredyExistException(ALREDY_EXIST);
            }
            departamento.setStatus(true);
        }
        return MapperDTO.parseObject(repository.saveAll(departamentos), DepartamentoDTO.class);
    }

    @Override
    @Cacheable("listSectors")
    public Page<DepartamentoDTO> list(Integer page, Integer pageSize) {
        Pageable pages = PageRequest.of(page, pageSize);
        Page<Departamento> pagedResult = repository.findAll(pages);

        Page<DepartamentoDTO> pagedDto = pagedResult.map(entity -> {
            return MapperDTO.parseObject(entity, DepartamentoDTO.class);
        });
        return pagedDto;
    }

    @Override
    public Optional<DepartamentoDTO> findSectorById(Integer id) {
        LOG.info("find users by id");
        var sector = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new DepartamentoNotFoundException(NOT_FOUND_SECTOR)));

        return Optional.ofNullable(MapperDTO.parseObject(Optional.of(sector.get()), DepartamentoDTO.class));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        LOG.info("delete departamento by id");
        Optional<Departamento> userExist = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new DepartamentoNotFoundException(NOT_FOUND_SECTOR)));

        repository.delete(userExist.get());
    }
    @Override
    @Transactional
    public DepartamentoDTO update(DepartamentoDTO dto, Integer id) {
        LOG.info("updating users");

        var entity = repository.findById(id)
                .orElseThrow(() -> new DepartamentoNotFoundException(NOT_FOUND_SECTOR));

        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        return MapperDTO.parseObject(repository.save(entity), DepartamentoDTO.class);
    }
    @Override
    public void disable(Integer id_sector){
        var entity = repository.findById(id_sector)
                .orElseThrow(() -> new DepartamentoNotFoundException(NOT_FOUND_SECTOR));

        entity.setStatus(false);
        MapperDTO.parseObject(repository.save(entity), DepartamentoDTO.class);
    }
}
