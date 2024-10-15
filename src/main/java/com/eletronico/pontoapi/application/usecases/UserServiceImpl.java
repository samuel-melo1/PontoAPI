package com.eletronico.pontoapi.application.usecases;

import com.eletronico.pontoapi.core.exceptions.NotPermitDeleteAdmException;
import com.eletronico.pontoapi.core.exceptions.ObjectAlreadyExistException;
import com.eletronico.pontoapi.core.exceptions.ObjectNotFoundException;
import com.eletronico.pontoapi.infrastructure.persistence.UserRepository;
import com.eletronico.pontoapi.utils.MapperDTO;
import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.entrypoint.dto.request.UserDTO;
import com.eletronico.pontoapi.application.gateways.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
            throw new ObjectAlreadyExistException(ALREDY_EXIST);
        }
        User newUser = User.builder()
                .id_user(null)
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .telefone(userDTO.getTelefone())
                .status(true)
                .cpf(userDTO.getCpf())
                .cargo(userDTO.getCargo())
                .departamento(userDTO.getDepartamento())
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
                                .cargo(user.getCargo())
                                .departamento(user.getDepartamento())
                                .permissions(user.getPermissions())
                                .totalElements(findTotalElementsInDataBase()).build()).collect(Collectors.toList()), UserDTO.class);
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        LOG.info("find users by email");
        var userExist = Optional.ofNullable(userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(NOT_EXIST)));
        return Optional.of(MapperDTO.parseObject(Optional.of(userExist.get()), UserDTO.class));
    }
    public Long findTotalElementsInDataBase() {
        return userRepository.count();
    }

    @Override
    public Optional<UserDTO> findUserById(Integer id) {
        LOG.info("find users by id");
        var userExist = Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(NOT_EXIST)));
        return Optional.ofNullable(MapperDTO.parseObject(Optional.of(userExist.get()), UserDTO.class));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        LOG.info("delete users by id");
        var user = findUserById(id);
        userRepository.deleteById(user.get().getId());
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO dto, Integer id) {
        LOG.info("updating users");

        dto.setId(id);
        Optional<UserDTO> oldUser = findUserById(id);

        if(oldUser.isPresent() && dto.getPassword() != oldUser.get().getPassword()){
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        User newUser = new User();
        BeanUtils.copyProperties(oldUser, newUser);
        return MapperDTO.parseObject(userRepository.save(newUser), UserDTO.class);
    }

    @Transactional
    public void disableUser(Integer id_user) {
        var entityUser = userRepository.findById(id_user)
                .orElseThrow(() -> new ObjectNotFoundException(NOT_EXIST));
        MapperDTO.parseObject(userRepository.save(entityUser), UserDTO.class);
    }
}
