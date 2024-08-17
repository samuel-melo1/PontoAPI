package com.eletronico.pontoapi.application.usecases;

import com.eletronico.pontoapi.core.domain.Position;
import com.eletronico.pontoapi.core.domain.Sector;
import com.eletronico.pontoapi.core.enums.PositionExceptionStatusError;
import com.eletronico.pontoapi.core.enums.SectionExceptionStatusError;
import com.eletronico.pontoapi.core.exceptions.*;
import com.eletronico.pontoapi.infrastructure.persistence.PositionRepository;
import com.eletronico.pontoapi.infrastructure.persistence.SectorRepository;
import com.eletronico.pontoapi.infrastructure.persistence.UserRepository;
import com.eletronico.pontoapi.utils.GenericValidAdministrator;
import com.eletronico.pontoapi.utils.MapperDTO;
import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.entrypoint.dto.request.UserDTO;
import com.eletronico.pontoapi.application.gateways.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eletronico.pontoapi.core.enums.UserExceptionStatusError.ALREDY_EXIST;
import static com.eletronico.pontoapi.core.enums.UserExceptionStatusError.NOT_EXIST;
import static com.eletronico.pontoapi.core.enums.UserExceptionStatusError.NOT_PERMITED_DELETE;
import static com.eletronico.pontoapi.core.enums.UserExceptionStatusError.NOT_PERMITED_DISABLE;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class.getName());

    @Transactional
    @Override
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
                .permissions(userDTO.getPermissions()).build();
        return MapperDTO.parseObject(userRepository.save(newUser), UserDTO.class);
    }

    @Override
     @Cacheable("users")
    public List<UserDTO> listUser(Integer page, Integer pageSize) {
        return MapperDTO.parseListObjects(
                userRepository.findAll(PageRequest.of(page, pageSize))
                        .stream()
                        .map(user -> UserDTO.builder()
                                .id(user.getId_user())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .telefone(user.getTelefone())
                                .cpf(user.getCpf())
                                .status(user.getStatus())
                                .name(user.getName())
                                .position(user.getPosition())
                                .sector(user.getSector())
                                .userRole(user.getUserRole())
                                .permissions(user.getPermissions())
                                .totalElements(findTotalElementsInDataBase()).build()).collect(Collectors.toList()), UserDTO.class);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        LOG.info("find users by email");
        var userExist = Optional.ofNullable(userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST)));
        return Optional.of(userExist.get());
    }

    @Cacheable("totalElements")
    public Long findTotalElementsInDataBase() {
        return userRepository.count();
    }

    @Override
    public Optional<UserDTO> findUserById(Integer id) {
        LOG.info("find users by id");
        var userExist = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST)));
        return Optional.ofNullable(MapperDTO.parseObject(Optional.of(userExist.get()), UserDTO.class));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        LOG.info("delete users by id");
        var user = findUserById(id);
        GenericValidAdministrator.verify(user.get().getPermissions(), new NotPermitDeleteAdmException(NOT_PERMITED_DELETE));
        userRepository.deleteById(user.get().getId());
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO dto, Integer id) {
        LOG.info("updating users");

        User userPersisted = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST));

        userPersisted.setEmail(dto.getEmail());
        userPersisted.setName(dto.getName());
        userPersisted.setTelefone(dto.getTelefone());
        userPersisted.setCpf(dto.getCpf());
        userPersisted.setStatus(dto.getStatus());
        LOG.info("user persisted ");

        userPersisted.setPosition(dto.getPosition());
        userPersisted.setSector(dto.getSector());
        userPersisted.setPermissions(dto.getPermissions());
        return MapperDTO.parseObject(userRepository.save(userPersisted), UserDTO.class);
    }

    @Transactional
    public void disableUser(Integer id_user) {
        var entityUser = userRepository.findById(id_user)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST));

        GenericValidAdministrator.verify(entityUser.getPermissions(), new NotPermitDeleteAdmException(NOT_PERMITED_DISABLE));

        entityUser.setAccountNonExpired(false);
        entityUser.setAccountNonLocked(false);
        entityUser.setCredentialsNonExpired(false);
        entityUser.setEnabled(false);
        entityUser.setStatus(false);
        MapperDTO.parseObject(userRepository.save(entityUser), UserDTO.class);
    }
}
