package com.confra.api.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectsIsNullException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RequiredObjectsIsNullException() {
        super("Não é permitido passar um atributo sem valor!");
    }

    public RequiredObjectsIsNullException(String msg) {
        super(msg);
    }
}
