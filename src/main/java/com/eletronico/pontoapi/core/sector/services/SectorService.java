package com.eletronico.pontoapi.core.sector.services;

import com.eletronico.pontoapi.adapters.database.sector.SectorRepository;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.sector.dto.SectorDTO;
import com.eletronico.pontoapi.core.sector.exceptions.SectionAlredyExistException;
import com.eletronico.pontoapi.core.user.services.UserService;
import com.eletronico.pontoapi.utils.mapper.MapperDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.eletronico.pontoapi.core.sector.enums.SectionExceptionStatusError.ALREDY_EXIST;
@Service
@Slf4j
public class SectorService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class.getName());
    private SectorRepository repository;
    private ModelMapper mapper;

    public SectorService(SectorRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SectorDTO create(SectorDTO sector) {
        LOG.info("creating sector");
        var entity = repository.findById(sector.getCode());
        if (entity.isPresent()) {
            throw new SectionAlredyExistException(ALREDY_EXIST);
        }

        Sector newSector = Sector.builder()
                .name(sector.getName()).build();
        return MapperDTO.parseObject(repository.save(newSector), SectorDTO.class);
    }

    public List<Sector> list() {
        LOG.info("finding all sectors");
        return repository.findAll();
    }
}
