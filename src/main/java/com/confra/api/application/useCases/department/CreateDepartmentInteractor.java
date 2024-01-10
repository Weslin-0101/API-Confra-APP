package com.confra.api.application.useCases.department;

import com.confra.api.application.gateways.department.CreateDepartmentGateway;
import com.confra.api.domain.DepartmentEntity;

public class CreateDepartmentInteractor {
    private final CreateDepartmentGateway createDepartmentGateway;

    public CreateDepartmentInteractor (CreateDepartmentGateway createDepartmentGateway) {
        this.createDepartmentGateway = createDepartmentGateway;
    }

    public DepartmentEntity createDepartment(DepartmentEntity departmentEntity) {
        return createDepartmentGateway.createDepartment(departmentEntity);
    }
}
