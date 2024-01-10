package com.confra.api.application.useCases.department;

import com.confra.api.application.gateways.department.UpdateDepartmentGateway;
import com.confra.api.domain.DepartmentEntity;
import com.confra.api.exceptions.RequiredObjectsIsNullException;

public class UpdateDepartmentInteractor {
    private final UpdateDepartmentGateway updateDepartmentGateway;

    public UpdateDepartmentInteractor(UpdateDepartmentGateway updateDepartmentGateway) {
        this.updateDepartmentGateway = updateDepartmentGateway;
    }

    public DepartmentEntity updateDepartment(String name, DepartmentEntity newDepartment) {
        if (name == null || name.isEmpty()) throw new RequiredObjectsIsNullException("Name is null");
        return updateDepartmentGateway.updateDepartment(name, newDepartment);
    }
}
