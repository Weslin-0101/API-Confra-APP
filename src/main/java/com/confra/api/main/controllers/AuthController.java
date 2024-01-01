package com.confra.api.main.controllers;

import com.confra.api.main.controllers.dtos.authentication.AuthenticateRequest;
import com.confra.api.main.controllers.dtos.authentication.AuthenticationResponse;
import com.confra.api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("root/confra/auth/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticateRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    };
}
