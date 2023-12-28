package com.confra.api.application.gateways;

import com.confra.api.domain.UserEntity;

public interface UserGateway {
    UserEntity createUser (UserEntity user);
}
