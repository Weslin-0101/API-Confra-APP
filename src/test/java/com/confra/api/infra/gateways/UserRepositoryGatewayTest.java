package com.confra.api.infra.gateways;

import com.confra.api.domain.UserEntity;
import com.confra.api.infra.persistence.repositories.UserRepository;
import com.confra.api.infra.persistence.tables.User;
import com.confra.api.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryGatewayTest {

    @InjectMocks
    UserRepositoryGateway sut;

    @Mock
    UserRepository userRepository;
    @Mock
    UserEntityMapper userEntityMapper;

    MockUser mockUser = new MockUser();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new UserRepositoryGateway(userRepository, userEntityMapper);
    }

    @Test
    void shouldCreateUserRepositoryGateway() {
        UserEntity userEntity = mockUser.mockUserEntity();
        User savedUser = new User();
        savedUser.setId(UUID.randomUUID());

        Mockito.when(userEntityMapper.toEntity(userEntity)).thenReturn(savedUser);
        Mockito.when(userRepository.save(savedUser)).thenReturn(savedUser);
        Mockito.when(userEntityMapper.toDomainObject(savedUser)).thenReturn(userEntity);

        UserEntity result = sut.createUser(userEntity);

        Mockito.verify(userRepository, Mockito.times(1)).save(savedUser);

        assertEquals(userEntity, result);
    }
}