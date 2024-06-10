package com.eletronico.pontoapi.core.role.services.impl;

import com.eletronico.pontoapi.adapters.database.role.RoleRepository;
import com.eletronico.pontoapi.adapters.utils.mapper.MapperDTO;
import com.eletronico.pontoapi.core.role.domain.Role;
import com.eletronico.pontoapi.core.role.dto.RoleDTO;
import com.eletronico.pontoapi.core.role.services.RoleService;
import com.eletronico.pontoapi.core.sector.exceptions.SectionAlredyExistException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eletronico.pontoapi.core.sector.enums.SectionExceptionStatusError.ALREDY_EXIST;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;
    @Transactional
    @Override
    public RoleDTO create(RoleDTO dto){

        var entity = repository.findById(dto.getCode());
        if (entity.isPresent()) {
            throw new SectionAlredyExistException(ALREDY_EXIST);
        }
        Role newRole = Role.builder()
                .name(dto.getName()).build();
        return MapperDTO.parseObject(repository.save(newRole), RoleDTO.class);
    }
    @Override
    public List<Role> list(){
        return repository.findAll();
    }
}
