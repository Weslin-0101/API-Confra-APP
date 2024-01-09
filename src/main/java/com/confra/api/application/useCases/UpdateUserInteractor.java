package com.confra.api.application.useCases;

import com.confra.api.application.gateways.UpdateUserGateway;
import com.confra.api.domain.UserEntity;

public class UpdateUserInteractor {
    private final UpdateUserGateway updateGateway;

    public UpdateUserInteractor(UpdateUserGateway updateGateway) {
        this.updateGateway = updateGateway;
    }

    public UserEntity updateUser(String email, UserEntity newUser) {
        return updateGateway.updateUser(email, newUser);
    }
}
