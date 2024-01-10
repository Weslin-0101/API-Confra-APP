package com.confra.api.infra.useCases;

import com.confra.api.main.controllers.dtos.authentication.AuthenticateRequest;
import com.confra.api.main.controllers.dtos.authentication.AuthenticationResponse;

public interface AuthenticationUserUseCase {
    AuthenticationResponse authenticate(AuthenticateRequest request);
}
