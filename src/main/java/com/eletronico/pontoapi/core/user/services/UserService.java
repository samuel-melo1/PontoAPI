package com.eletronico.pontoapi.core.user.services;

import com.eletronico.pontoapi.core.user.domain.User;
import com.eletronico.pontoapi.core.user.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO saveUser(UserDTO dto);

    List<UserDTO> listUser(Integer page, Integer pageSize);

    Optional<User> findUserByEmail(String email);

    void delete(Integer id);

    UserDTO update(UserDTO dto);

    void disableUser(Integer id_user);

    Optional<UserDTO> findUserById(Integer id);
}
