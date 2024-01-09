package com.confra.api.infra.gateways;

import com.confra.api.application.gateways.user.DeleteUserByEmailGateway;
import com.confra.api.application.gateways.user.UpdateUserGateway;
import com.confra.api.application.gateways.user.UserFindByEmailGateway;
import com.confra.api.application.gateways.user.UserGateway;
import com.confra.api.domain.UserEntity;
import com.confra.api.exceptions.RequestNotAllowedException;
import com.confra.api.exceptions.RequiredObjectsIsNullException;
import com.confra.api.exceptions.ResourceNotFoundException;
import com.confra.api.infra.persistence.repositories.UserRepository;
import com.confra.api.infra.persistence.tables.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserRepositoryGateway implements
        UserGateway,
        UserFindByEmailGateway,
        UpdateUserGateway,
        DeleteUserByEmailGateway {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    public UserRepositoryGateway(
            UserRepository userRepository, UserEntityMapper userEntityMapper, PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.passwordEncoder = passwordEncoder;
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

        userDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));

        User userPersistence = userEntityMapper.toEntity(userDomain);
        User savedUserEntity = userRepository.save(userPersistence);

        return userEntityMapper.toDomainObject(savedUserEntity);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        Optional<User> findUser = userRepository.findByEmail(email);
        if (findUser.isEmpty()) {
            throw new RequestNotAllowedException("User not found");
        }

        return userEntityMapper.toDomainObject(findUser.get());
    }

    @Override
    public UserEntity updateUser(String email, UserEntity newUser) {
        var entity = userRepository.findByEmail(email)
                .orElseThrow(ResourceNotFoundException::new);
        entity.setCpf(newUser.getCpf());
        entity.setDtRegistration(newUser.getDtRegistration());
        entity.setName(newUser.getName());
        entity.setLastname(newUser.getLastname());
        entity.setEmail(newUser.getEmail());
        entity.setTotalInstallments(newUser.getTotalInstallments());
        entity.setTotalInstallmentsPaid(newUser.getTotalInstallmentsPaid());


        User savedUserEntity = userRepository.save(entity);
        return userEntityMapper.toDomainObject(savedUserEntity);
    }

    @Override
    public void deleteUserByEmail(String email) {
        Optional<User> findUser = userRepository.findByEmail(email);
        if (findUser.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        userRepository.delete(findUser.get());
    }
}
