package com.confra.api.application.gateways;

import com.confra.api.domain.UserEntity;

public interface UpdateUserGateway {
    UserEntity updateUser(String email, UserEntity newUser);
}
