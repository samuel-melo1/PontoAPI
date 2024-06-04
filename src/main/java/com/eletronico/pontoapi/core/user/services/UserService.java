package com.eletronico.pontoapi.core.user.services;

import com.eletronico.pontoapi.adapters.database.user.UserRepository;
import com.eletronico.pontoapi.core.role.domain.Role;
import com.eletronico.pontoapi.core.user.domain.User;
import com.eletronico.pontoapi.core.user.dto.EditListUserDTO;
import com.eletronico.pontoapi.core.user.dto.StandardListUserDTO;
import com.eletronico.pontoapi.core.user.dto.UserDTO;
import com.eletronico.pontoapi.core.user.enums.UserRole;
import com.eletronico.pontoapi.core.user.exceptions.NotPermitedDeleteAdmException;
import com.eletronico.pontoapi.core.user.exceptions.UserAlredyExistException;
import com.eletronico.pontoapi.core.user.exceptions.UserNotFoundException;
import com.eletronico.pontoapi.adapters.utils.mapper.MapperDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.eletronico.pontoapi.core.user.enums.UserExceptionStatusError.*;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper mapper;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class.getName());

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
                .sector(userDTO.getSector())
                .name(userDTO.getName())
                .permissions(userDTO.getRole()).build();

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
    public Optional<User> findUserByEmail(String email) {
        LOG.info("find users by email");
        Optional<User> userExist = Optional.ofNullable(userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST)));

        return Optional.of(userExist.get());
    }
    @Transactional
    public void delete(Integer id) {
        LOG.info("delete users by id");
        Optional<User> userExist = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST)));

        for (String roles : userExist.get().getRoles()){
            if(Objects.equals(roles, UserRole.ADMINISTRADOR.name())){
                throw new NotPermitedDeleteAdmException(NOT_PERMITED_DELETE);
            }
        }
        userRepository.delete(userExist.get());
    }
    public EditListUserDTO update(EditListUserDTO dto) {
        LOG.info("updating users");

        User entity = userRepository.findUserByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST));

        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setTelefone(dto.getTelefone());
        entity.setCpf(dto.getCpf());
        entity.setPosition(dto.getPosition());
        entity.setSector(dto.getSector());
        entity.setPermissions(dto.getPermissions());

        return MapperDTO.parseObject(userRepository.save(entity), EditListUserDTO.class);
    }

    public void disableUser(Integer id_user){
        var entityUser = userRepository.findById(id_user)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST));

        entityUser.setAccountNonExpired(false);
        entityUser.setAccountNonLocked(false);
        entityUser.setCredentialsNonExpired(false);
        entityUser.setEnabled(false);
        entityUser.setStatus(false);
    }
}
