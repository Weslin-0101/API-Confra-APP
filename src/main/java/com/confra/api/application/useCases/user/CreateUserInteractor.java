package com.confra.api.application.useCases.user;

import com.confra.api.application.gateways.user.UserGateway;
import com.confra.api.domain.UserEntity;
import com.confra.api.exceptions.RequiredObjectsIsNullException;

public class CreateUserInteractor {
    private final UserGateway userGateway;

    public CreateUserInteractor (UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public UserEntity createUser (UserEntity userEntity) {
        if (userEntity == null) throw new RequiredObjectsIsNullException();
        return userGateway.createUser(userEntity);
    }
}
