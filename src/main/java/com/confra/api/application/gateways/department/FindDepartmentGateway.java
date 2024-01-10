package com.confra.api.application.gateways.department;

import com.confra.api.domain.DepartmentEntity;

public interface FindDepartmentGateway {
    DepartmentEntity findDepartmentById (String name);
}
