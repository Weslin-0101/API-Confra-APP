package com.confra.api.application.useCases;

import com.confra.api.application.gateways.UserFindByEmailGateway;
import com.confra.api.domain.UserEntity;
import com.confra.api.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FindUserByEmailInteractorTest {
    @InjectMocks
    private FindUserByEmailInteractor sut;
    @Mock
    private UserFindByEmailGateway userGateway;
    private MockUser mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new MockUser();
    }

    @Test
    void shouldReturnAnUserByEmail() {
        var request = mockUser.mockUserEntity();
        UserEntity expectedResult = mockUser.mockUserEntity();

        Mockito.when(userGateway.findUserByEmail(request.getEmail())).thenReturn(expectedResult);

        UserEntity result = sut.findUserByEmail(request.getEmail());

        assertNotNull(result);
        assertEquals(expectedResult.getEmail(), request.getEmail());
    }
}