package com.eletronico.pontoapi.core.user.services.impl;

import com.eletronico.pontoapi.core.domain.Departamento;
import com.eletronico.pontoapi.infrastructure.persistence.UserRepository;
import com.eletronico.pontoapi.application.usecases.UserServiceImpl;
import com.eletronico.pontoapi.core.domain.Cargo;
import com.eletronico.pontoapi.core.domain.Role;
import com.eletronico.pontoapi.core.domain.User;
import com.eletronico.pontoapi.entrypoint.dto.request.UserDTO;
import com.eletronico.pontoapi.core.enums.UserRole;
import com.eletronico.pontoapi.core.exceptions.NotPermitDisableAdmException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static com.eletronico.pontoapi.core.enums.UserExceptionStatusError.NOT_EXIST;

@SpringBootTest
@ContextConfiguration
@ExtendWith(SpringExtension.class)
@DisplayName("User Test Service")
class UserServiceImplTest {
/*
    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ModelMapper mapper;

    private UserDTO userDto;
    private User user;
    private Optional<User> userOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startMockUser();
    }
    @Test
    @DisplayName("User created with success in service")
    void testCreateUser_whenCreateUserThenReturnSuccess() {
        when(repository.existsPessoaByEmail(anyString())).thenReturn(false);
        when(repository.save(any())).thenReturn(user);
        when(mapper.map(any(User.class), any(Class.class))).thenReturn(userDto);

        UserDTO response = service.saveUser(userDto);
        assertNotNull(response);
        assertEquals(UserDTO.class, response.getClass());
        assertEquals("samuel", response.getName());
        assertEquals("samuel@gmail.com", response.getEmail());
        assertEquals("48996859940", response.getTelefone());
        assertEquals("12256131912", response.getCpf());
        assertEquals(UserRole.ADMINISTRADOR, response.getUserRole());
        assertEquals(new Cargo(1, "Programador", true, null), response.getCargo());
        assertEquals(new Departamento(1, "Engenharia", true, null), response.getDepartamento());
        assertEquals(List.of(new Role(1, "Colaborador")), response.getPermissions());

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).existsPessoaByEmail(anyString());
    }

    @Test
    @DisplayName("User already exist in service")
    void whenCreateUserThenReturnUserAlreadyExistException() {
        when(repository.existsPessoaByEmail(anyString())).thenReturn(true);

        Exception thrown = assertThrows(UserAlredyExistException.class, () -> {
            service.saveUser(userDto);
        });

        assertEquals(UserAlredyExistException.class, thrown.getClass());
        assertEquals("User alredy Exist. Please, try other!", thrown.getMessage());
    }

    @Test
    @DisplayName("Find user by email return success")
    void whenFindUserByEmailThenReturnAnUserInstance() {
        when(repository.findUserByEmail(anyString())).thenReturn(userOptional);

        Optional<User> response = service.findUserByEmail("samuel@gmail.com");

        assertNotNull(response);
        assertEquals(Optional.class, response.getClass());
        assertEquals("samuel", response.get().getName());
        assertEquals("samuel@gmail.com", response.get().getEmail());
        assertEquals("48996859940", response.get().getTelefone());
        assertEquals("12256131912", response.get().getCpf());
        assertEquals(UserRole.ADMINISTRADOR, response.get().getUserRole());
        assertEquals(new Cargo(1, "Programador", true, null), response.get().getCargo());
        assertEquals(new Departamento(1, "Engenharia", true, null), response.get().getDepartamento());
        assertEquals(List.of("Colaborador"), response.get().getRoles());

        verify(repository, times(1)).findUserByEmail(anyString());
    }
    @Test
    @DisplayName("Find user by email return exception ")
    void whenFindUserByEmailThenReturnUserNotFoundException() {
        when(repository.findUserByEmail(anyString())).thenReturn(Optional.empty());

        Exception thrown = assertThrows(UserNotFoundException.class, () -> {
            service.findUserByEmail(userOptional.get().getEmail());
        });

        assertEquals(UserNotFoundException.class, thrown.getClass());
        assertEquals("User Not Found!", thrown.getMessage());
    }
    @Test
    @WithMockUser
    @DisplayName("Disable user return success")
    void whenDisableUserCadasterReturnSuccess() {
        when(repository.findById(anyInt())).thenReturn(userOptional);
        when(repository.save(any())).thenReturn(user);
        when(mapper.map(any(User.class), any(Class.class))).thenReturn(userDto);


        service.disableUser(1);
        verify(repository, times(1)).save(any());
        verify(repository, times(1)).findById(anyInt());
    }
    @Test
    @DisplayName("Disable user return exception")
    void whenDisableUserCadasterThenReturnUserNotFoundException() {
        when(repository.findById(anyInt())).thenReturn(Optional.empty());

        Exception thrown = assertThrows(UserNotFoundException.class, () -> {
            service.disableUser(2);
        });

        assertEquals(UserNotFoundException.class, thrown.getClass());
        assertEquals("User Not Found!", thrown.getMessage());
    }

    @Test
    @DisplayName("Disable user when user is administrator return exception")
    void whenDisableUserCadasterThenReturnNotPermitedDeleteException() {

        User user1 = new User(1, "samuel@gmail.com", "samuel", "1234", "48996859940",
                "12256131912", true, UserRole.ADMINISTRADOR,
                true, true, true, true,
                new Cargo(1, "Programador", true, null),
                new Departamento(1, "Engenharia", true, null),
                List.of(new Role(1, "Colaborador")), new ArrayList<>());

        when(repository.findById(anyInt())).thenReturn(Optional.of(user1));

        Exception thrown = assertThrows(NotPermitDisableAdmException.class, () -> {
            service.disableUser(1);
        });

        assertEquals(UserNotFoundException.class, thrown.getClass());
        assertEquals("User Not Found!", thrown.getMessage());
    }
    @Test
    @DisplayName("updated user return success")
    void whenUpdateUserThenReturnSuccess() {
        when(repository.findUserByEmail(anyString())).thenReturn(userOptional);
        when(repository.save(any())).thenReturn(user);
        when(mapper.map(any(User.class), any(Class.class))).thenReturn(userDto);

        UserDTO response = service.update(userDto, 1);

        assertNotNull(response);
        assertEquals(UserDTO.class, response.getClass());
        assertEquals(1, response.getId());
        assertEquals("samuel", response.getName());
        assertEquals("samuel@gmail.com", response.getEmail());
        assertEquals("1234", response.getPassword());
        assertEquals("48996859940", response.getTelefone());
        assertEquals("12256131912", response.getCpf());
    }
    @Test
    @DisplayName("Update user return exception")
    void whenUpdateUserThenReturnUserNotFoundException() {
        when(repository.findUserByEmail(anyString())).thenThrow(new UserNotFoundException(NOT_EXIST));

        Exception thrown = assertThrows(UserNotFoundException.class, () -> {
            service.update(userDto, 1);
        });

        assertEquals(UserNotFoundException.class, thrown.getClass());
        assertEquals("User Not Found!", thrown.getMessage());
        verify(repository, times(1)).findUserByEmail(anyString());
    }

    @Test
    @DisplayName("Delete user return success")
    void whenDeleteUserThenReturnSuccess() {

        when(repository.findById(anyInt())).thenReturn(userOptional);

        doNothing().when(repository).deleteById(user.getId_user());
        service.delete(1);
        verify(repository, times(1)).deleteById(anyInt());
        verify(repository, times(1)).findById(anyInt());
    }

    @Test
    @DisplayName("Delete user return exception")
    void whenDeleteUserThenReturnUserNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new UserNotFoundException(NOT_EXIST));

        Exception thrown = assertThrows(UserNotFoundException.class, () -> {
            service.delete(1);
        });

        assertEquals(UserNotFoundException.class, thrown.getClass());
        assertEquals("User Not Found!", thrown.getMessage());
        verify(repository, times(1)).findById(anyInt());
    }

    private void startMockUser() {

        user = new User();
        user.setId_user(1);
        user.setEmail("samuel@gmail.com");
        user.setPassword("1234");
        user.setTelefone("48996859940");
        user.setCpf("12256131912");
        user.setUserRole(UserRole.ADMINISTRADOR);
        user.setName("samuel");
        user.setCargo(new Cargo(1, "Programador", true, null));
        user.setDepartamento(new Departamento(1, "Engenharia", true, null));
        user.setPermissions(List.of(new Role(1, "Colaborador")));

        userDto = new UserDTO();
        userDto.setEmail("samuel@gmail.com");
        userDto.setPassword("1234");
        userDto.setTelefone("48996859940");
        userDto.setCpf("12256131912");
        userDto.setUserRole(UserRole.ADMINISTRADOR);
        userDto.setName("samuel");
        userDto.setCargo(new Cargo(1, "Programador", true, null));
        userDto.setDepartamento(new Departamento(1, "Engenharia", true, null));
        userDto.setPermissions(List.of(new Role(1, "Colaborador")));

        userOptional = Optional.of(
                new User(1, "samuel@gmail.com", "samuel", "1234", "48996859940", "12256131912", true, UserRole.ADMINISTRADOR,
                        true, true, true, true, new Cargo(1, "Programador", true, null),
                        new Departamento(1, "Engenharia", true, null),
                        List.of(new Role(1, "Colaborador")),  new ArrayList<>())
        );
    }
*/
}