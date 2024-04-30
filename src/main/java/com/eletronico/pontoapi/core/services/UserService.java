package com.eletronico.pontoapi.core.services;

import com.eletronico.pontoapi.adapters.database.UserRepository;
import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.core.dto.StandardListUserDTO;
import com.eletronico.pontoapi.core.dto.UserDTO;
import com.eletronico.pontoapi.core.exceptions.UserAlredyExistException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }
    @Transactional
    public User saveUser(UserDTO userDTO) {

        if (userRepository.existsPessoaByEmail(userDTO.email())) {
            throw new UserAlredyExistException(userDTO.email());
        }
        User newUser = User.builder()
                .email(userDTO.email())
                .password(passwordEncoder.encode(userDTO.password()))
                .userRole(userDTO.userRole())
                .telefone(userDTO.telefone())
                .status(true)
                .name(userDTO.name()).build();

        return userRepository.save(newUser);
    }
    public Page<StandardListUserDTO> listUser(Integer page, Integer pageSize) {
        Pageable pages = PageRequest.of(page, pageSize);
        Page<User> pagedResult = userRepository.findAll(pages);

        Page<StandardListUserDTO> pagedDto = pagedResult.map(entity -> {
            StandardListUserDTO dto = entityToDo(entity);
            return dto;
        });
        return pagedDto;
    }
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
    public StandardListUserDTO entityToDo(User user) {
        StandardListUserDTO standardListUserDTO = new StandardListUserDTO();
        standardListUserDTO.setName(user.getEmail());
        standardListUserDTO.setTelefone(user.getTelefone());
        standardListUserDTO.setEmail(user.getEmail());
        standardListUserDTO.setUserRole(user.getUserRole());
        return standardListUserDTO;
    }
}
