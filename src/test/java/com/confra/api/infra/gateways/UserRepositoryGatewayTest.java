package com.confra.api.infra.gateways;

import com.confra.api.domain.UserEntity;
import com.confra.api.exceptions.RequiredObjectsIsNullException;
import com.confra.api.infra.persistence.repositories.UserRepository;
import com.confra.api.infra.persistence.tables.User;
import com.confra.api.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryGatewayTest {

    @InjectMocks
    private UserRepositoryGateway sut;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserEntityMapper userEntityMapper;
    private MockUser mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = new MockUser();
    }

    @Test
    public void shouldCreateAnUser() {
        UserEntity request = mockUser.mockUserEntity();

        User userPersistence = userEntityMapper.toEntity(request);

        Mockito.when(userRepository.save(userPersistence)).thenReturn(userPersistence);
        Mockito.when(userEntityMapper.toDomainObject(userPersistence)).thenReturn(request);

        UserEntity result = sut.createUser(request);

        assertNotNull(result);
        assertEquals(request.getCpf(), result.getCpf());
        assertEquals(request.getDtRegistration(), result.getDtRegistration());
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getLastname(), result.getLastname());
        assertEquals(request.getEmail(), result.getEmail());
        assertEquals(request.getPassword(), result.getPassword());
        assertEquals(request.getTotalInstallments(), result.getTotalInstallments());
        assertEquals(request.getTotalInstallmentsPaid(), result.getTotalInstallmentsPaid());
    }

    @Test
    public void shouldThrowExceptionWhenUserIsNull() {
        RequiredObjectsIsNullException exception = assertThrows(RequiredObjectsIsNullException.class,
                () -> sut.createUser(null));

        assertEquals("User cannot be null", exception.getMessage());
    }
}