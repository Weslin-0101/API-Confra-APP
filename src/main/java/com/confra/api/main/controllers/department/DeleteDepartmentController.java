package com.confra.api.main.controllers.department;

import com.confra.api.application.useCases.department.DeleteDepartmentInteractor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class DeleteDepartmentController {
    private final DeleteDepartmentInteractor deleteDepartmentUseCase;

    public DeleteDepartmentController(DeleteDepartmentInteractor deleteDepartmentUseCase) {
        this.deleteDepartmentUseCase = deleteDepartmentUseCase;
    }

    @DeleteMapping("{name}")
    public ResponseEntity<?> deleteDepartment (@PathVariable(value = "name") String name) {
        deleteDepartmentUseCase.deleteDepartment(name);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
