package com.confra.api.application.useCases.user;

import com.confra.api.application.gateways.user.DeleteUserByEmailGateway;
import com.confra.api.application.useCases.user.DeleteUserByEmailInteractor;
import com.confra.api.exceptions.ResourceNotFoundException;
import com.confra.api.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class DeleteUserByEmailInteractorTest {
    @InjectMocks
    private DeleteUserByEmailInteractor sut;
    @Mock
    private DeleteUserByEmailGateway deleteUserByEmailGateway;
    private MockUser user = new MockUser();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new MockUser();
    }

    @Test
    void shouldCallDeleteAnUserByEmailGateway() {
        var request = user.mockUserEntity();

        sut.deleteUserByEmail(request.getEmail());

        Mockito.verify(deleteUserByEmailGateway).deleteUserByEmail(request.getEmail());
    }

    @Test
    void shouldThrowExceptionIfEmailsNotMatch() {
        var request = user.mockUserEntity();

        Mockito.doThrow(ResourceNotFoundException.class)
                .when(deleteUserByEmailGateway)
                .deleteUserByEmail(request.getEmail());
    }

    @Test
    void shouldThrowExceptionIfEmailIsNull() {
        var request = user.mockUserEntity();
        request.setEmail(null);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> sut.deleteUserByEmail(request.getEmail()));
        assertEquals("Não foi possível encontrar esse usuário", exception.getMessage());
    }
}