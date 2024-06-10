package com.eletronico.pontoapi.core.user.services;

import com.eletronico.pontoapi.core.user.domain.User;
import com.eletronico.pontoapi.core.user.dto.EditListUserDTO;
import com.eletronico.pontoapi.core.user.dto.StandardListUserDTO;
import com.eletronico.pontoapi.core.user.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    UserDTO saveUser(UserDTO dto);
    Page<StandardListUserDTO> listUser(Integer page, Integer pageSize);
    Optional<User> findUserByEmail(String email);
    void delete(Integer id);
    EditListUserDTO update(EditListUserDTO dto);
    void disableUser(Integer id_user);
}
