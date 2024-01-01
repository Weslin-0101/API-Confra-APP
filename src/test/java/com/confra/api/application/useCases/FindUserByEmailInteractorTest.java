package com.confra.api.application.useCases;

import com.confra.api.application.gateways.UserFindByEmailGateway;
import com.confra.api.application.gateways.UserGateway;
import com.confra.api.domain.UserEntity;
import com.confra.api.mocks.MockUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class FindUserByEmailInteractorTest {

    @Test
    void shouldReturnAnUserByEmail() {
        FindUserByEmailInteractor sut = new FindUserByEmailInteractor();

        MockUser mock = new MockUser();
        var request = mock.mockUserEntity();
        UserEntity expectedResult = mock.mockUserEntity();

        UserEntity result = sut.findUserByEmail(request.getEmail());

        assertNotNull(result);
        assertEquals(expectedResult.getEmail(), result.getEmail());
    }
}