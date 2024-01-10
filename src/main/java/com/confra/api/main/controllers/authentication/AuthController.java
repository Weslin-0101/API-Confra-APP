package com.confra.api.main.controllers.authentication;

import com.confra.api.infra.useCases.AuthenticationUserInteractor;
import com.confra.api.main.controllers.dtos.authentication.AuthenticateRequest;
import com.confra.api.main.controllers.dtos.authentication.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("confra/auth/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationUserInteractor authService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticateRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    };
}
