package com.confra.api.application.useCases;

import com.confra.api.application.gateways.DeleteUserByEmailGateway;

public class DeleteUserByEmailInteractor {
    private final DeleteUserByEmailGateway deleteUserByEmailGateway;

    public DeleteUserByEmailInteractor(DeleteUserByEmailGateway deleteUserByEmailGateway) {
        this.deleteUserByEmailGateway = deleteUserByEmailGateway;
    }
    public void deleteUserByEmail(String email) {
        deleteUserByEmailGateway.deleteUserByEmail(email);
    }
}
