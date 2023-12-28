package com.confra.api.application.useCases;

import com.confra.api.application.gateways.UserGateway;
import com.confra.api.domain.UserEntity;

public class CreateUserInteractor {
    private final UserGateway userGateway;

    public CreateUserInteractor (UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public UserEntity createUser (UserEntity userEntity) {
        return userGateway.createUser(userEntity);
    }
}
