package com.confra.api.mocks;

import com.confra.api.domain.DepartmentEntity;

import java.util.Date;

public class MockDepartment {
    public DepartmentEntity mockDepartmentEntity() {
        return new DepartmentEntity(
                "Departamento teste",
                "Descrição teste",
                "Supervisor teste",
                new Date()
        );
    }
}
