package com.confra.api.application.useCases.user;

import com.confra.api.application.gateways.user.DeleteUserByEmailGateway;
import com.confra.api.exceptions.ResourceNotFoundException;

public class DeleteUserByEmailInteractor {
    private final DeleteUserByEmailGateway deleteUserByEmailGateway;

    public DeleteUserByEmailInteractor(DeleteUserByEmailGateway deleteUserByEmailGateway) {
        this.deleteUserByEmailGateway = deleteUserByEmailGateway;
    }
    public void deleteUserByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        deleteUserByEmailGateway.deleteUserByEmail(email);
    }
}
