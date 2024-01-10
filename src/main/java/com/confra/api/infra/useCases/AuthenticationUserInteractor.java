package com.confra.api.infra.useCases;

import com.confra.api.exceptions.ResourceNotFoundException;
import com.confra.api.infra.adapters.JwtAdapter;
import com.confra.api.infra.persistence.repositories.UserRepository;
import com.confra.api.main.controllers.dtos.authentication.AuthenticateRequest;
import com.confra.api.main.controllers.dtos.authentication.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthenticationUserInteractor implements AuthenticationUserUseCase {
    private final UserRepository userRepository;
    private final JwtAdapter jwtAdapter;
    private final AuthenticationManager authenticationManager;

    public AuthenticationUserInteractor(UserRepository userRepository, JwtAdapter jwtAdapter, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtAdapter = jwtAdapter;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(ResourceNotFoundException::new);
            var jwtToken = jwtAdapter.generateToken(user);

            return AuthenticationResponse.builder()
                    .name(user.getName())
                    .accessToken(jwtToken)
                    .build();
        } catch (Exception e) {
            throw new BadCredentialsException("E-mail e/ou senha inválida");
        }
    }
}
