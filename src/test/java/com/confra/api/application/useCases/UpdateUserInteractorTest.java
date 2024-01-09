package com.confra.api.application.useCases;

import com.confra.api.application.gateways.UpdateUserGateway;
import com.confra.api.domain.UserEntity;
import com.confra.api.infra.persistence.tables.User;
import com.confra.api.mocks.MockUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateUserInteractorTest {
    @InjectMocks
    private UpdateUserInteractor sut;
    @Mock
    private UpdateUserGateway updateGateway;
    private MockUser mockUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = new MockUser();
    }

    @Test
    public void shouldUpdateAnUser() {
        UserEntity user = mockUser.mockUserEntity();
        UserEntity newUser = new UserEntity(
                "11111111111",
                new Date(),
                "New name",
                "User",
                "teste@gmail.com",
                "123456",
                4,
                4
        );

        Mockito.when(updateGateway.updateUser(user.getEmail(), newUser)).thenReturn(newUser);

        var result = sut.updateUser(user.getEmail(), newUser);

        assertNotNull(result);
        assertEquals(newUser.getName(), result.getName());
        assertEquals(user.getEmail(), result.getEmail());
        assertNotEquals(user.getName(), result.getName());
    }
}
