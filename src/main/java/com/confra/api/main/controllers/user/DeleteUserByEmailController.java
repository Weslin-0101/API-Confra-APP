package com.confra.api.main.controllers.user;

import com.confra.api.application.useCases.DeleteUserByEmailInteractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "confra/api/v1/user")
public class DeleteUserByEmailController {
    private final DeleteUserByEmailInteractor deleteUserByEmailUseCase;

    public DeleteUserByEmailController(DeleteUserByEmailInteractor deleteUserByEmailUseCase) {
        this.deleteUserByEmailUseCase = deleteUserByEmailUseCase;
    }

    @DeleteMapping("{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable(value = "email") String email) {
        deleteUserByEmailUseCase.deleteUserByEmail(email);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
