package com.confra.api.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAllowedDuplicateValueException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotAllowedDuplicateValueException() {
        super("Esse e-mail já possui cadastro na plataforma!");
    }

    public NotAllowedDuplicateValueException(String msg) {
        super(msg);
    }
}
