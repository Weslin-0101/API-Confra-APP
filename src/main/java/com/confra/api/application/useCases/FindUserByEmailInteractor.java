package com.confra.api.application.useCases;

import com.confra.api.domain.UserEntity;

public class FindUserByEmailInteractor {
    public FindUserByEmailInteractor() {}

    public UserEntity findUserByEmail (String email) {
        UserEntity user = new UserEntity();
        user.setEmail("teste@gmail.com");

        return user;
    }
}
