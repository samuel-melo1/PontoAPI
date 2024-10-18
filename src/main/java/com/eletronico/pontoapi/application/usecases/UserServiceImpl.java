package com.eletronico.pontoapi.application.usecases;

import com.eletronico.pontoapi.application.gateways.CargoService;
import com.eletronico.pontoapi.application.gateways.DepartamentoService;
import com.eletronico.pontoapi.core.domain.Cargo;
import com.eletronico.pontoapi.core.domain.Departamento;
import com.eletronico.pontoapi.core.exceptions.DataIntegrityException;
import com.eletronico.pontoapi.core.exceptions.ObjectAlreadyExistException;
import com.eletronico.pontoapi.core.exceptions.ObjectNotFoundException;
import com.eletronico.pontoapi.entrypoint.dto.request.CargoDTO;
import com.eletronico.pontoapi.entrypoint.dto.request.DepartamentoDTO;
import com.eletronico.pontoapi.infrastructure.persistence.UserRepository;
import com.eletronico.pontoapi.utils.MapperDTO;
import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.entrypoint.dto.request.UserDTO;
import com.eletronico.pontoapi.application.gateways.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eletronico.pontoapi.core.enums.DataIntegrityViolationError.CPF_ALREADY_EXIST;
import static com.eletronico.pontoapi.core.enums.DataIntegrityViolationError.EMAIL_ALREADY_EXIST;
import static com.eletronico.pontoapi.core.enums.UserExceptionStatusError.ALREDY_EXIST;
import static com.eletronico.pontoapi.core.enums.UserExceptionStatusError.NOT_EXIST;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CargoService cargoService;
    @Autowired
    private DepartamentoService departamentoService;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class.getName());

    @Transactional
    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        LOG.info("creating a new user");
        userDTO.setId_user(null);

        Optional<CargoDTO> cargoObj = cargoService.findById(userDTO.getCargo().getId_cargo());
        Optional<DepartamentoDTO> departamentoObj = departamentoService.findById(userDTO.getDepartamento().getId_departamento());

        validaPorCpfEEmail(userDTO);
        User newUser = User.builder()
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .telefone(userDTO.getTelefone())
                .status(true)
                .cpf(userDTO.getCpf())
                .cargo(MapperDTO.parseObject(cargoObj, Cargo.class))
                .departamento(MapperDTO.parseObject(departamentoObj, Departamento.class))
                .name(userDTO.getName())
                .permissions(userDTO.getPermissions()).build();

        return MapperDTO.parseObject(userRepository.save(newUser), UserDTO.class);
    }

    @Override
    public List<UserDTO> listUser(Integer page, Integer pageSize) {
        return MapperDTO.parseListObjects(
                userRepository.findAll(PageRequest.of(page, pageSize)).toList(), UserDTO.class);
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        LOG.info("find users by email");
        var userExist = Optional.ofNullable(userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ObjectNotFoundException(NOT_EXIST)));
        return Optional.of(MapperDTO.parseObject(Optional.of(userExist.get()), UserDTO.class));
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
        userRepository.deleteById(user.get().getId_user());
    }

    @Override
    @Transactional
    public UserDTO update(UserDTO userDTO, Integer id) {
        LOG.info("updating users");

        userDTO.setId_user(id);
        Optional<UserDTO> oldUser = findUserById(id);

        if (!userDTO.getPassword().equals(oldUser.get().getPassword())) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        validaPorCpfEEmail(userDTO);

        User newUser = new User();
        BeanUtils.copyProperties(userDTO, newUser);
        return MapperDTO.parseObject(userRepository.save(newUser), UserDTO.class);
    }

    @Transactional
    public void disableUser(Integer id_user) {
        var entityUser = userRepository.findById(id_user)
                .orElseThrow(() -> new ObjectNotFoundException(NOT_EXIST));

        entityUser.setStatus(false);
        MapperDTO.parseObject(userRepository.save(entityUser), UserDTO.class);
    }

    private void validaPorCpfEEmail(UserDTO dto) {
        Optional<User> obj = userRepository.findByCpf(dto.getCpf());
        if (obj.isPresent() && obj.get().getId_user() != dto.getId_user()) {
            throw new DataIntegrityException(CPF_ALREADY_EXIST);
        }
        obj = userRepository.findByEmail(dto.getEmail());
        if (obj.isPresent() && obj.get().getId_user() != dto.getId_user()) {
            throw new DataIntegrityException(EMAIL_ALREADY_EXIST);
        }

    }
}
