package com.confra.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidJwtAuthenticationException extends AuthenticationException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidJwtAuthenticationException() {
        super("Token JWT inválido ou expirado");
    }

    public InvalidJwtAuthenticationException(String msg) {
        super(msg);
    }
}
