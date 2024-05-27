package com.eletronico.pontoapi.core.sector.services;

import com.eletronico.pontoapi.adapters.database.sector.SectorRepository;
import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.position.dto.PositionDTO;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.sector.dto.SectorDTO;
import com.eletronico.pontoapi.core.sector.exceptions.SectionAlredyExistException;
import com.eletronico.pontoapi.core.user.domain.User;
import com.eletronico.pontoapi.core.user.enums.UserRole;
import com.eletronico.pontoapi.core.user.exceptions.NotPermitedDeleteAdmException;
import com.eletronico.pontoapi.core.user.exceptions.UserNotFoundException;
import com.eletronico.pontoapi.core.user.services.UserService;
import com.eletronico.pontoapi.adapters.utils.mapper.MapperDTO;
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
import java.util.Objects;
import java.util.Optional;

import static com.eletronico.pontoapi.core.sector.enums.SectionExceptionStatusError.ALREDY_EXIST;
import static com.eletronico.pontoapi.core.user.enums.UserExceptionStatusError.NOT_EXIST;
import static com.eletronico.pontoapi.core.user.enums.UserExceptionStatusError.NOT_PERMITED_DELETE;

@Service
@Slf4j
public class SectorService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class.getName());
    @Autowired
    private SectorRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Transactional
    public SectorDTO create(List<Sector> sectors) {
        LOG.info("creating sector");
        for (Sector sector : sectors) {
            var entity = repository.findByName(sector.getName());
            if (entity != null) {
                throw new SectionAlredyExistException(ALREDY_EXIST);
            }
        }
        return MapperDTO.parseObject(repository.saveAll(sectors), SectorDTO.class);
    }
    public Page<SectorDTO> list(Integer page, Integer pageSize) {
        Pageable pages = PageRequest.of(page, pageSize);
        Page<Sector> pagedResult = repository.findAll(pages);

        Page<SectorDTO> pagedDto = pagedResult.map(entity -> {
            return MapperDTO.parseObject(entity, SectorDTO.class);
        });
        return pagedDto;
    }
    @Transactional
    public void delete(Integer id) {
        LOG.info("delete sector by id");
        Optional<Sector> userExist = Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST)));

        repository.delete(userExist.get());
    }
}
