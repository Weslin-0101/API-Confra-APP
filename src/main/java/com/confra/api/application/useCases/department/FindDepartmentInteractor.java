package com.confra.api.application.useCases.department;

import com.confra.api.application.gateways.department.FindDepartmentGateway;
import com.confra.api.domain.DepartmentEntity;
import com.confra.api.exceptions.RequiredObjectsIsNullException;

public class FindDepartmentInteractor {
    private FindDepartmentGateway findDepartmentGateway;

    public FindDepartmentInteractor(FindDepartmentGateway findDepartmentGateway) {
        this.findDepartmentGateway = findDepartmentGateway;
    }

    public DepartmentEntity findDepartment (String name) {
        if (name == null) throw new RequiredObjectsIsNullException("Name is null");

        return findDepartmentGateway.findDepartmentById(name);
    }
}
