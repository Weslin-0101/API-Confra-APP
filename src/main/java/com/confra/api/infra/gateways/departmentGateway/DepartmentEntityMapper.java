package com.confra.api.infra.gateways.departmentGateway;

import com.confra.api.domain.DepartmentEntity;
import com.confra.api.infra.gateways.util.EntityMapper;
import com.confra.api.infra.persistence.tables.Department;

public class DepartmentEntityMapper implements EntityMapper<Department, DepartmentEntity> {
    @Override
    public Department toEntity(DepartmentEntity departmentEntity) {
        Department department = new Department();
        department.setName(departmentEntity.getName());
        department.setDescription(departmentEntity.getDescription());
        department.setSupervisor(departmentEntity.getSupervisor());
        department.setDtRegistration(departmentEntity.getDtRegistration());

        return department;
    }

    @Override
    public DepartmentEntity toDomainObject(Department department) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName(department.getName());
        departmentEntity.setDescription(department.getDescription());
        departmentEntity.setSupervisor(department.getSupervisor());
        departmentEntity.setDtRegistration(department.getDtRegistration());

        return departmentEntity;
    }
}
