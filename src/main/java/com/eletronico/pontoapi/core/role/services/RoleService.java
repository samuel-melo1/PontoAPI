package com.eletronico.pontoapi.core.role.services;

import com.eletronico.pontoapi.adapters.database.role.RoleRepository;
import com.eletronico.pontoapi.core.role.domain.Role;
import com.eletronico.pontoapi.core.role.dto.RoleDTO;
import com.eletronico.pontoapi.core.sector.exceptions.SectionAlredyExistException;
import com.eletronico.pontoapi.adapters.utils.mapper.MapperDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eletronico.pontoapi.core.sector.enums.SectionExceptionStatusError.ALREDY_EXIST;

@Service
@Slf4j
public class RoleService {

    @Autowired
    private RoleRepository repository;
    @Transactional
    public RoleDTO create(RoleDTO dto){

        var entity = repository.findById(dto.getCode());
        if (entity.isPresent()) {
            throw new SectionAlredyExistException(ALREDY_EXIST);
        }
        Role newRole = Role.builder()
                .name(dto.getName()).build();
        return MapperDTO.parseObject(repository.save(newRole), RoleDTO.class);
    }
    public List<Role> list(){
        return repository.findAll();
    }
}
