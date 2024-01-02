package com.confra.api.application.useCases;

import com.confra.api.application.gateways.UserGateway;
import com.confra.api.domain.UserEntity;
import com.confra.api.exceptions.RequiredObjectsIsNullException;
import com.confra.api.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateUserInteractorTest {
    @InjectMocks
    private CreateUserInteractor sut;
    @Mock
    private UserGateway userGateway;
    private MockUser mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new MockUser();
    }

    @Test
    void shouldCreateAnUser() throws Exception {
        UserEntity request = mockUser.mockUserEntity();
        UserEntity expectedResult = new UserEntity();

        Mockito.when(userGateway.createUser(request)).thenReturn(expectedResult);

        UserEntity result = sut.createUser(request);

        assertNotNull(result);
        assertEquals(expectedResult.getCpf(), result.getCpf());
        assertEquals(expectedResult.getDtRegistration(), result.getDtRegistration());
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getLastname(), result.getLastname());
        assertEquals(expectedResult.getEmail(), result.getEmail());
        assertEquals(expectedResult.getPassword(), result.getPassword());
        assertEquals(expectedResult.getTotalInstallments(), result.getTotalInstallments());
        assertEquals(expectedResult.getTotalInstallmentsPaid(), result.getTotalInstallmentsPaid());
    }

    @Test
    void shouldThrowExceptionWhenUserIsNull() {
        assertThrows(RequiredObjectsIsNullException.class,
                () -> sut.createUser(null));
    }
}