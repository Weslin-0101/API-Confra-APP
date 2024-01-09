package com.confra.api.application.gateways.user;

import com.confra.api.domain.UserEntity;

public interface UserGateway {
    UserEntity createUser (UserEntity user);
}
