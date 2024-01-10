package com.confra.api.main.config.user;

import com.confra.api.application.gateways.user.DeleteUserByEmailGateway;
import com.confra.api.application.gateways.user.UpdateUserGateway;
import com.confra.api.application.gateways.user.UserFindByEmailGateway;
import com.confra.api.application.gateways.user.UserGateway;
import com.confra.api.application.useCases.user.CreateUserInteractor;
import com.confra.api.application.useCases.user.DeleteUserByEmailInteractor;
import com.confra.api.application.useCases.user.FindUserByEmailInteractor;
import com.confra.api.application.useCases.user.UpdateUserInteractor;
import com.confra.api.infra.adapters.JwtAdapter;
import com.confra.api.infra.useCases.AuthenticationUserInteractor;
import com.confra.api.infra.useCases.AuthenticationUserUseCase;
import com.confra.api.main.controllers.dtos.user.UserDTOMapper;
import com.confra.api.infra.gateways.UserEntityMapper;
import com.confra.api.infra.gateways.UserRepositoryGateway;
import com.confra.api.infra.persistence.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {
    private final UserRepository repository;

    public UserConfig(UserRepository repository) {
        this.repository = repository;
    }

    @Bean
    CreateUserInteractor createUserUsecase(UserGateway userGateway) {
        return new CreateUserInteractor(userGateway);
    }

    @Bean
    FindUserByEmailInteractor findUserByEmailUsecase (UserFindByEmailGateway userFindByEmailGateway) {
        return new FindUserByEmailInteractor(userFindByEmailGateway);
    }

    @Bean
    UpdateUserInteractor updateUserInteractor (UpdateUserGateway updateUserGateway) {
        return new UpdateUserInteractor(updateUserGateway);
    }

    @Bean
    DeleteUserByEmailInteractor deleteUserByEmailUsecase (DeleteUserByEmailGateway deleteUserByEmailGateway) {
        return new DeleteUserByEmailInteractor(deleteUserByEmailGateway);
    }

    @Bean
    AuthenticationUserInteractor authenticationUserInteractor (
            UserRepository userRepository,
            JwtAdapter jwtAdapter,
            AuthenticationManager authenticationManager
    ) {
        return new AuthenticationUserInteractor(userRepository, jwtAdapter, authenticationManager);
    }

    @Bean
    UserGateway userGateway(
            UserRepository userRepository,
            UserEntityMapper userEntityMapper,
            PasswordEncoder passwordEncoder
    ) {
        return new UserRepositoryGateway(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    UserFindByEmailGateway userFindByEmailGateway(
            UserRepository userRepository,
            UserEntityMapper userEntityMapper,
            PasswordEncoder passwordEncoder
    ) {
        return new UserRepositoryGateway(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    UpdateUserGateway updateUserGateway(
            UserRepository userRepository,
            UserEntityMapper userEntityMapper,
            PasswordEncoder passwordEncoder
    ) {
        return new UserRepositoryGateway(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    DeleteUserByEmailGateway deleteUserByEmailGateway(
            UserRepository userRepository,
            UserEntityMapper userEntityMapper,
            PasswordEncoder passwordEncoder
    ) {
        return new UserRepositoryGateway(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }

    @Bean
    JwtAdapter jwtAdapter() {
        return new JwtAdapter();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEnconder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEnconder() {
        return new BCryptPasswordEncoder();
    }
}