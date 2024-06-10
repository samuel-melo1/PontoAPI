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

public interface RoleService {
    RoleDTO create(RoleDTO dto);
    List<Role> list();
}
