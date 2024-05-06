package com.eletronico.pontoapi.core.services;

import com.eletronico.pontoapi.adapters.database.UserRepository;
import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.core.dto.StandardListUserDTO;
import com.eletronico.pontoapi.core.dto.UserDTO;
import com.eletronico.pontoapi.core.exceptions.UserAlredyExistException;
import com.eletronico.pontoapi.core.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static com.eletronico.pontoapi.core.enums.UserExceptionStatusError.ALREDY_EXIST;
import static com.eletronico.pontoapi.core.enums.UserExceptionStatusError.NOT_EXIST;

@Service
public class UserService  {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }
    @Transactional
    public User saveUser(UserDTO userDTO) {

        if (userRepository.existsPessoaByEmail(userDTO.email())) {
            throw new UserAlredyExistException(ALREDY_EXIST);
        }
        User newUser = User.builder()
                .email(userDTO.email())
                .password(passwordEncoder.encode(userDTO.password()))
                .userRole(userDTO.userRole())
                .telefone(userDTO.telefone())
                .status(true)
                .cpf(userDTO.cpf())
                .position(userDTO.position())
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
    public Optional<User> findByEmail(String email) {
        Optional<User> userExist = Optional.ofNullable(userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST)));

        return userRepository.findUserByEmail(String.valueOf(userExist));
    }

    public void delete(Integer id){
        Optional<User> userExist = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST)));

        userRepository.delete(userExist.get());
    }
    public StandardListUserDTO entityToDo(User user) {
        StandardListUserDTO standardListUserDTO = new StandardListUserDTO();
        standardListUserDTO.setId(user.getId_user());
        standardListUserDTO.setName(user.getName());
        standardListUserDTO.setTelefone(user.getTelefone());
        standardListUserDTO.setEmail(user.getEmail());
        standardListUserDTO.setUserRole(user.getUserRole());
        standardListUserDTO.setCpf(user.getCpf());
        return standardListUserDTO;
    }
}
