package com.eletronico.pontoapi.core.user.services.impl;

import com.eletronico.pontoapi.adapters.database.user.UserRepository;
import com.eletronico.pontoapi.core.position.domain.Position;
import com.eletronico.pontoapi.core.role.domain.Role;
import com.eletronico.pontoapi.core.sector.domain.Sector;
import com.eletronico.pontoapi.core.user.domain.User;
import com.eletronico.pontoapi.core.user.dto.UserDTO;
import com.eletronico.pontoapi.core.user.enums.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

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


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startMockUser();
    }

    @Test
    void whenCreateUserThenReturnSuccess() {
        when(repository.existsPessoaByEmail(anyString())).thenReturn(false);
        when(repository.save(any())).thenReturn(user);
        when(mapper.map(any(User.class), any(Class.class))).thenReturn(userDto);

        UserDTO response = service.saveUser(userDto);
        assertNotNull(response);
        assertEquals(UserDTO.class, response.getClass());
        assertEquals("samuel", response.getName());
        assertEquals("samuel@gmail.com", response.getEmail());

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).existsPessoaByEmail(anyString());
        verify(service, times(1)).saveUser(userDto);
    }

    @Test
    void listUser() {

    }

    @Test
    void findUserByEmail() {

    }

    @Test
    void delete() {

    }

    @Test
    void update() {

    }

    @Test
    void disableUser() {

    }

    private void startMockUser() {

        user = new User();
        user.setEmail("samuel@gmail.com");
        user.setPassword("1234");
        user.setTelefone("48996859940");
        user.setCpf("12256131912");
        user.setUserRole(UserRole.ADMINISTRADOR);
        user.setName("samuel");
        user.setPosition(new Position(1, "Programador", true, null));
        user.setSector(new Sector(1, "Engenharia", true, null));
        user.setPermissions(List.of(new Role(1, "Colaborador")));


        userDto = new UserDTO();
        userDto.setEmail("samuel@gmail.com");
        userDto.setPassword("1234");
        userDto.setTelefone("48996859940");
        userDto.setCpf("12256131912");
        userDto.setUserRole(UserRole.ADMINISTRADOR);
        userDto.setName("samuel");
        userDto.setPosition(new Position(1, "Programador", true, null));
        userDto.setSector(new Sector(1, "Engenharia", true, null));
        userDto.setRole(List.of(new Role(1, "Colaborador")));

    }

}