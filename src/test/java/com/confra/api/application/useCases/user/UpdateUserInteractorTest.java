package com.confra.api.application.useCases.user;

import com.confra.api.application.gateways.user.UpdateUserGateway;
import com.confra.api.application.useCases.user.UpdateUserInteractor;
import com.confra.api.domain.UserEntity;
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
                4,
                "LrvuydPKplZEwXc5ay11sS0MAaqdJ4W8nTLnlNNTpGh9DRDh2S03IjG2WUiyWpVr",
                false
        );

        Mockito.when(updateGateway.updateUser(user.getEmail(), newUser)).thenReturn(newUser);

        var result = sut.updateUser(user.getEmail(), newUser);

        assertNotNull(result);
        assertEquals(newUser.getName(), result.getName());
        assertEquals(user.getEmail(), result.getEmail());
        assertNotEquals(user.getName(), result.getName());
    }
}
