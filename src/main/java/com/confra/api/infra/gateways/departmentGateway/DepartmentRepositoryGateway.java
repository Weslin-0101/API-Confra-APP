package com.confra.api.infra.gateways.departmentGateway;

import com.confra.api.application.gateways.department.CreateDepartmentGateway;
import com.confra.api.domain.DepartmentEntity;
import com.confra.api.exceptions.RequestNotAllowedException;
import com.confra.api.exceptions.RequiredObjectsIsNullException;
import com.confra.api.infra.persistence.repositories.DepartmentRepository;
import com.confra.api.infra.persistence.tables.Department;

import java.util.Optional;

public class DepartmentRepositoryGateway implements CreateDepartmentGateway {
    private final DepartmentRepository departmentRepository;
    private final DepartmentEntityMapper departmentEntityMapper;

    public DepartmentRepositoryGateway(DepartmentRepository departmentRepository, DepartmentEntityMapper departmentEntityMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentEntityMapper = departmentEntityMapper;
    }

    @Override
    public DepartmentEntity createDepartment(DepartmentEntity departmentEntity) {
        if (departmentEntity == null) {
            throw new RequiredObjectsIsNullException("Department cannot be null");
        }

        Optional<Department> findDepartment = departmentRepository.findByName(departmentEntity.getName());
        if (findDepartment.isPresent()) {
            throw new RequestNotAllowedException("Department already exists");
        }

        Department departmentPersistence = departmentEntityMapper.toEntity(departmentEntity);
        Department savedDepartmentEntity = departmentRepository.save(departmentPersistence);

        return departmentEntityMapper.toDomainObject(savedDepartmentEntity);
    }
}
