package com.confra.api.application.useCases.department;

import com.confra.api.application.gateways.department.DeleteDepartmentGateway;
import com.confra.api.exceptions.RequiredObjectsIsNullException;
import com.confra.api.exceptions.ResourceNotFoundException;

public class DeleteDepartmentInteractor {
    private DeleteDepartmentGateway deleteDepartmentGateway;

    public DeleteDepartmentInteractor (DeleteDepartmentGateway deleteDepartmentGateway) {
        this.deleteDepartmentGateway = deleteDepartmentGateway;
    }

    public void deleteDepartment (String departmentName) {
        if (departmentName == null || departmentName.isEmpty())
            throw new RequiredObjectsIsNullException("Nome do departamento é nulo ou vazio");
        deleteDepartmentGateway.deleteDepartment(departmentName);
    }
}
