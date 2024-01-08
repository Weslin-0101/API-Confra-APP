package com.confra.api.infra.gateways;

import com.confra.api.application.gateways.UserGateway;
import com.confra.api.domain.UserEntity;
import com.confra.api.exceptions.RequestNotAllowedException;
import com.confra.api.exceptions.RequiredObjectsIsNullException;
import com.confra.api.infra.persistence.repositories.UserRepository;
import com.confra.api.infra.persistence.tables.User;

import java.util.Optional;

public class UserRepositoryGateway implements UserGateway {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserEntity createUser(UserEntity userDomain) {
        if (userDomain == null) {
            throw new RequiredObjectsIsNullException("User cannot be null");
        }

        Optional<User> findUser = userRepository.findByEmail(userDomain.getEmail());
        if (findUser.isPresent()) {
            throw new RequestNotAllowedException("User already exists");
        }

        User userPersistence = userEntityMapper.toEntity(userDomain);
        User savedUserEntity = userRepository.save(userPersistence);

        return userEntityMapper.toDomainObject(savedUserEntity);
    }
}
