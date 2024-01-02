package com.confra.api.application.useCases;

import com.confra.api.application.gateways.UserFindByEmailGateway;
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
