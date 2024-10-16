package com.eletronico.pontoapi.application.usecases;

import com.eletronico.pontoapi.core.exceptions.ObjectAlreadyExistException;
import com.eletronico.pontoapi.core.exceptions.ObjectNotFoundException;
import com.eletronico.pontoapi.entrypoint.dto.request.DepartamentoDTO;
import com.eletronico.pontoapi.infrastructure.persistence.RoleRepository;
import com.eletronico.pontoapi.utils.MapperDTO;
import com.eletronico.pontoapi.core.domain.Role;
import com.eletronico.pontoapi.entrypoint.dto.request.RoleDTO;
import com.eletronico.pontoapi.application.gateways.RoleService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.eletronico.pontoapi.core.enums.DepartamentoExceptionStatusError.ALREDY_EXIST;
import static com.eletronico.pontoapi.core.enums.RoleExceptionStatusError.NOT_FOUND_ROLE;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;
    @Transactional
    @Override
    public RoleDTO create(RoleDTO dto){

        var entity = repository.findByName(dto.getName());
        if (entity.isPresent()) {
            throw new ObjectAlreadyExistException(ALREDY_EXIST);
        }
        Role newRole = Role.builder()
                .name(dto.getName()).build();
        return MapperDTO.parseObject(repository.save(newRole), RoleDTO.class);
    }
    @Override
    @Cacheable("listRoles")
    public List<RoleDTO> findAll(){
        return MapperDTO.parseListObjects(repository.findAll(), RoleDTO.class);
    }

    @Override
    public Optional<RoleDTO> findById(Integer id) {
       var role = Optional.of(repository.findById(id))
               .orElseThrow(() -> new ObjectNotFoundException(NOT_FOUND_ROLE));

       return Optional.ofNullable(MapperDTO.parseObject(Optional.of(role), RoleDTO.class));
    }
}
