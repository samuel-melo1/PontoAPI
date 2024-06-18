package com.eletronico.pontoapi.core.sector.services.impl;

import com.eletronico.pontoapi.adapters.database.sector.SectorRepository;
import com.eletronico.pontoapi.adapters.utils.mapper.MapperDTO;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.sector.dto.SectorDTO;
import com.eletronico.pontoapi.core.sector.exceptions.SectionAlredyExistException;
import com.eletronico.pontoapi.core.sector.exceptions.SectionNotFoundException;
import com.eletronico.pontoapi.core.sector.services.SectorService;
import com.eletronico.pontoapi.core.user.dto.UserDTO;
import com.eletronico.pontoapi.core.user.exceptions.UserNotFoundException;
import com.eletronico.pontoapi.core.user.services.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static com.eletronico.pontoapi.core.sector.enums.SectionExceptionStatusError.ALREDY_EXIST;
import static com.eletronico.pontoapi.core.sector.enums.SectionExceptionStatusError.NOT_FOUND_USER;
import static com.eletronico.pontoapi.core.user.enums.UserExceptionStatusError.NOT_EXIST;

@Service
@Slf4j
public class SectorServiceImpl implements SectorService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class.getName());
    @Autowired
    private SectorRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Transactional
    @Override
    public SectorDTO create(List<Sector> sectors) {
        LOG.info("creating sector");
        for (Sector sector : sectors) {
            var entity = repository.findByName(sector.getName());
            if (entity.isPresent()) {
                throw new SectionAlredyExistException(ALREDY_EXIST);
            }
            sector.setStatus(true);
        }
        return MapperDTO.parseObject(repository.saveAll(sectors), SectorDTO.class);
    }

    @Override
    public Page<SectorDTO> list(Integer page, Integer pageSize) {
        Pageable pages = PageRequest.of(page, pageSize);
        Page<Sector> pagedResult = repository.findAll(pages);

        Page<SectorDTO> pagedDto = pagedResult.map(entity -> {
            return MapperDTO.parseObject(entity, SectorDTO.class);
        });
        return pagedDto;
    }

    @Override
    public Optional<SectorDTO> findSectorById(Integer id) {
        LOG.info("find users by id");
        var sector = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException(NOT_FOUND_USER)));

        return Optional.ofNullable(MapperDTO.parseObject(Optional.of(sector.get()), SectorDTO.class));
    }
    @Override
    public List<SectorDTO> listAll() {
        return MapperDTO.parseListObjects(repository.findAll(), SectorDTO.class);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        LOG.info("delete sector by id");
        Optional<Sector> userExist = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new SectionNotFoundException(NOT_FOUND_USER)));

        repository.delete(userExist.get());
    }
    @Override
    @Transactional
    public SectorDTO update(SectorDTO dto) {
        LOG.info("updating users");

        Sector entity = repository.findByName(dto.getName())
                .orElseThrow(() -> new SectionNotFoundException(NOT_FOUND_USER));

        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        return MapperDTO.parseObject(repository.save(entity), SectorDTO.class);
    }
    @Override
    public void disableSector(Integer id_user){
        var entityUser = repository.findById(id_user)
                .orElseThrow(() -> new SectionNotFoundException(NOT_FOUND_USER));

        entityUser.setStatus(false);
    }
}
