package com.confra.api.application.useCases.department;

import com.confra.api.application.gateways.department.CreateDepartmentGateway;
import com.confra.api.domain.DepartmentEntity;
import com.confra.api.exceptions.RequiredObjectsIsNullException;

public class CreateDepartmentInteractor {
    private final CreateDepartmentGateway createDepartmentGateway;

    public CreateDepartmentInteractor (CreateDepartmentGateway createDepartmentGateway) {
        this.createDepartmentGateway = createDepartmentGateway;
    }

    public DepartmentEntity createDepartment(DepartmentEntity departmentEntity) {
        if (departmentEntity == null) throw new RequiredObjectsIsNullException("Department is null");
        return createDepartmentGateway.createDepartment(departmentEntity);
    }
}
