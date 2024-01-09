package com.confra.api.application.useCases.user;

import com.confra.api.application.gateways.user.UserFindByEmailGateway;
import com.confra.api.domain.UserEntity;

public class FindUserByEmailInteractor {
    private final UserFindByEmailGateway userFindByEmailGateway;

    public FindUserByEmailInteractor(UserFindByEmailGateway userFindByEmailGateway) {
        this.userFindByEmailGateway = userFindByEmailGateway;
    }

    public UserEntity findUserByEmail (String email) {
        if (email == null) throw new NullPointerException("Email is null");

        return userFindByEmailGateway.findUserByEmail(email);
    }
}
