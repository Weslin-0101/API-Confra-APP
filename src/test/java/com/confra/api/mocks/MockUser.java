package com.confra.api.mocks;

import com.confra.api.domain.UserEntity;

import java.util.Date;

public class MockUser {
    public UserEntity mockUserEntity() {
        UserEntity userEntity = new UserEntity(
                "11111111111",
                new Date(),
                "Teste",
                "User",
                "teste@gmail.com",
                "123456",
                4,
                4
        );

        return userEntity;
    }
}
