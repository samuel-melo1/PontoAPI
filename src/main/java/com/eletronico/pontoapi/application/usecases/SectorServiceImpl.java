package com.eletronico.pontoapi.application.usecases;

import com.eletronico.pontoapi.infrastructure.persistence.SectorRepository;
import com.eletronico.pontoapi.utils.MapperDTO;
import com.eletronico.pontoapi.core.domain.Sector;
import com.eletronico.pontoapi.entrypoint.dto.request.SectorDTO;
import com.eletronico.pontoapi.core.exceptions.SectionAlredyExistException;
import com.eletronico.pontoapi.core.exceptions.SectorNotFoundException;
import com.eletronico.pontoapi.application.gateways.SectorService;
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
import static com.eletronico.pontoapi.core.enums.SectionExceptionStatusError.ALREDY_EXIST;
import static com.eletronico.pontoapi.core.enums.SectionExceptionStatusError.NOT_FOUND_SECTOR;

@Service
@Slf4j
public class SectorServiceImpl implements SectorService {
    private static final Logger LOG = LoggerFactory.getLogger(SectorServiceImpl.class.getName());

    @Autowired
    private SectorRepository repository;

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
    @Cacheable("listSectors")
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
                .orElseThrow(() -> new SectorNotFoundException(NOT_FOUND_SECTOR)));

        return Optional.ofNullable(MapperDTO.parseObject(Optional.of(sector.get()), SectorDTO.class));
    }
    @Override
    @Cacheable("listAllSectors")
    public List<SectorDTO> listAll() {
        return MapperDTO.parseListObjects(repository.findAll(), SectorDTO.class);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        LOG.info("delete sector by id");
        Optional<Sector> userExist = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException(NOT_FOUND_SECTOR)));

        repository.delete(userExist.get());
    }
    @Override
    @Transactional
    public SectorDTO update(SectorDTO dto, String name) {
        LOG.info("updating users");

        var entity = repository.findByName(name)
                .orElseThrow(() -> new SectorNotFoundException(NOT_FOUND_SECTOR));

        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        return MapperDTO.parseObject(repository.save(entity), SectorDTO.class);
    }
    @Override
    public void disableSector(Integer id_sector){
        var entity = repository.findById(id_sector)
                .orElseThrow(() -> new SectorNotFoundException(NOT_FOUND_SECTOR));

        entity.setStatus(false);
        MapperDTO.parseObject(repository.save(entity), SectorDTO.class);
    }
}
