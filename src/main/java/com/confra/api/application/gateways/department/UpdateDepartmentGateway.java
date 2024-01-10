package com.confra.api.application.gateways.department;

import com.confra.api.domain.DepartmentEntity;

public interface UpdateDepartmentGateway {
    DepartmentEntity updateDepartment(String name, DepartmentEntity newDepartment);
}
