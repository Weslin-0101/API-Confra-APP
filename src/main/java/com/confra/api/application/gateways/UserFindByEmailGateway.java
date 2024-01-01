package com.confra.api.application.gateways;

import com.confra.api.domain.UserEntity;

public interface UserFindByEmailGateway {
    UserEntity findUserByEmail (String email);
}
