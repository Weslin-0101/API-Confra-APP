package com.confra.api.application.gateways.user;

import com.confra.api.domain.UserEntity;

public interface UpdateUserGateway {
    UserEntity updateUser(String email, UserEntity newUser);
}
