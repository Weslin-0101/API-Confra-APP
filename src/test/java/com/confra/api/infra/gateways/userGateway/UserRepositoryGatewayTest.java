package com.confra.api.infra.gateways.userGateway;

import com.confra.api.domain.UserEntity;
import com.confra.api.exceptions.RequestNotAllowedException;
import com.confra.api.exceptions.RequiredObjectsIsNullException;
import com.confra.api.exceptions.ResourceNotFoundException;
import com.confra.api.infra.gateways.userGateway.UserEntityMapper;
import com.confra.api.infra.gateways.userGateway.UserRepositoryGateway;
import com.confra.api.infra.persistence.repositories.UserRepository;
import com.confra.api.infra.persistence.tables.User;
import com.confra.api.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryGatewayTest {

    @InjectMocks
    private UserRepositoryGateway sut;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserEntityMapper userEntityMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    private MockUser mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = new MockUser();
        userEntityMapper = Mockito.mock(UserEntityMapper.class);
        sut = new UserRepositoryGateway(userRepository, userEntityMapper, passwordEncoder);
    }

    @Test
    public void shouldCreateAnUser() {
        UserEntity request = mockUser.mockUserEntity();

        User userPersistence = userEntityMapper.toEntity(request);

        Mockito.when(userRepository.save(userPersistence)).thenReturn(userPersistence);
        Mockito.when(userEntityMapper.toDomainObject(userPersistence)).thenReturn(request);

        Mockito.when(passwordEncoder.encode(request.getPassword())).thenReturn("hashedPassword");

        UserEntity result = sut.createUser(request);

        assertNotNull(result);
        assertEquals(request.getCpf(), result.getCpf());
        assertEquals(request.getDtRegistration(), result.getDtRegistration());
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getLastname(), result.getLastname());
        assertEquals(request.getEmail(), result.getEmail());
        assertEquals("hashedPassword", result.getPassword());
        assertEquals(request.getTotalInstallments(), result.getTotalInstallments());
        assertEquals(request.getTotalInstallmentsPaid(), result.getTotalInstallmentsPaid());
    }

    @Test
    public void shouldThrowExceptionWhenUserIsNull() {
        RequiredObjectsIsNullException exception = assertThrows(RequiredObjectsIsNullException.class,
                () -> sut.createUser(null));

        assertEquals("User cannot be null", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenUserAlreadyExists() {
        UserEntity request = mockUser.mockUserEntity();

        User existenceUser = new User();
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(existenceUser));

        RequestNotAllowedException exception = assertThrows(RequestNotAllowedException.class,
                () -> sut.createUser(request));

        assertEquals("User already exists", exception.getMessage());
    }

    @Test
    public void shouldFindUserByEmail() {
        UserEntity request = mockUser.mockUserEntity();
        User userPersistence = new User();

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(userPersistence));
        Mockito.when(userEntityMapper.toDomainObject(userPersistence)).thenReturn(request);

        UserEntity result = sut.findUserByEmail(request.getEmail());

        assertNotNull(result);
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        UserEntity request = mockUser.mockUserEntity();

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        RequestNotAllowedException exception = assertThrows(RequestNotAllowedException.class,
                () -> sut.findUserByEmail(request.getEmail()));

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void shouldUpdateAnUser() {
        UserEntity request = mockUser.mockUserEntity();
        User userPersistence = new User();

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(userPersistence));
        Mockito.when(userEntityMapper.toDomainObject(userPersistence)).thenReturn(request);

        UserEntity result = sut.updateUser(request.getEmail(), request);

        assertNotNull(result);
    }

    @Test
    public void shouldThrowExceptionWhenEmailNotFound() {
        UserEntity request = mockUser.mockUserEntity();

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> sut.updateUser(request.getEmail(), request));

        assertEquals("Não foi possível encontrar esse usuário", exception.getMessage());
    }

    @Test
    public void shouldDeleteUserByEmail() {
        UserEntity request = mockUser.mockUserEntity();
        User userPersistence = new User();

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(userPersistence));

        sut.deleteUserByEmail(request.getEmail());

        Mockito.verify(userRepository, Mockito.times(1)).delete(userPersistence);
    }

    @Test
    public void shouldThrowIfEmailNotFound() {
        UserEntity request = mockUser.mockUserEntity();

        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> sut.deleteUserByEmail(request.getEmail()));

        assertEquals("Não foi possível encontrar esse usuário", exception.getMessage());
    }
}