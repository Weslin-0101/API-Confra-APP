package com.confra.api.application.useCases;

import com.confra.api.application.gateways.DeleteUserByEmailGateway;
import com.confra.api.domain.UserEntity;
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
}