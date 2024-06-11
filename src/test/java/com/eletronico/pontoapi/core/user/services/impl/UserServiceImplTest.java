package com.eletronico.pontoapi.core.user.services.impl;

import com.eletronico.pontoapi.adapters.database.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void saveUser() {

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
}