package com.eletronico.pontoapi.core.services;

import com.eletronico.pontoapi.adapters.database.UserRepository;
import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.core.dto.StandardListUserDTO;
import com.eletronico.pontoapi.core.dto.UserDTO;
import com.eletronico.pontoapi.core.exceptions.UserAlredyExistException;
import com.eletronico.pontoapi.core.exceptions.UserNotFoundException;
import com.eletronico.pontoapi.utils.mapper.MapperDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.eletronico.pontoapi.core.enums.UserExceptionStatusError.ALREDY_EXIST;
import static com.eletronico.pontoapi.core.enums.UserExceptionStatusError.NOT_EXIST;

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper mapper;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class.getName());

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        LOG.info("creating a new user");

        if (userRepository.existsPessoaByEmail(userDTO.getEmail())) {
            throw new UserAlredyExistException(ALREDY_EXIST);
        }
        User newUser = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .userRole(userDTO.getUserRole())
                .telefone(userDTO.getTelefone())
                .status(true)
                .cpf(userDTO.getCpf())
                .position(userDTO.getPosition())
                .name(userDTO.getName()).build();

        return mapper.map(userRepository.save(newUser), UserDTO.class);
    }

    public Page<StandardListUserDTO> listUser(Integer page, Integer pageSize) {
        LOG.info("Pagination list users");
        Pageable pages = PageRequest.of(page, pageSize);
        Page<User> pagedResult = userRepository.findAll(pages);

        Page<StandardListUserDTO> pagedDto = pagedResult.map(entity -> {
            return MapperDTO.parseObject(entity, StandardListUserDTO.class);

        });
        return pagedDto;
    }

    public Optional<UserDTO> findByEmail(String email) {
        LOG.info("find users by email");
        Optional<User> userExist = Optional.ofNullable(userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST)));

        return Optional.ofNullable(MapperDTO.parseObject(userExist, UserDTO.class));
    }
    public void delete(Integer id) {
        LOG.info("delete users by id");
        Optional<User> userExist = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST)));

        userRepository.delete(userExist.get());
    }
    public UserDTO update(UserDTO userDTO) {
        LOG.info("updating users");

        User entity = userRepository.findUserByEmail(userDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST));

        entity.setEmail(userDTO.getEmail());
        entity.setName(userDTO.getName());
        entity.setTelefone(userDTO.getTelefone());
        entity.setCpf(userDTO.getCpf());
        entity.setUserRole(userDTO.getUserRole());
        entity.setPosition(userDTO.getPosition());

        return MapperDTO.parseObject(userRepository.save(entity), UserDTO.class);
    }
}
