package com.confra.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RequestNotAllowedException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public RequestNotAllowedException() {
        super("Este usuário já fez check-in!");
    }

    public RequestNotAllowedException(String msg) {
        super(msg);
    }
}
