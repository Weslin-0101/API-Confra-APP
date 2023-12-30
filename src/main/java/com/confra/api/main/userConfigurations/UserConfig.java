package com.confra.api.main.userConfigurations;

import com.confra.api.application.gateways.UserGateway;
import com.confra.api.application.useCases.CreateUserInteractor;
import com.confra.api.infra.controllers.dtos.user.UserDTOMapper;
import com.confra.api.infra.gateways.UserEntityMapper;
import com.confra.api.infra.gateways.UserRepositoryGateway;
import com.confra.api.infra.persistence.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    CreateUserInteractor createUserUsecase(UserGateway userGateway) {
        return new CreateUserInteractor(userGateway);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserRepositoryGateway(userRepository, userEntityMapper);
    }

    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }
}
