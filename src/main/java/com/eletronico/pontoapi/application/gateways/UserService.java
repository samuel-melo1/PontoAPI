package com.eletronico.pontoapi.application.gateways;

import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.entrypoint.dto.request.UserDTO;

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
