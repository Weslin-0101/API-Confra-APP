package com.confra.api.infra.gateways.departmentGateway;

import com.confra.api.application.gateways.department.CreateDepartmentGateway;
import com.confra.api.application.gateways.department.FindDepartmentGateway;
import com.confra.api.application.gateways.department.UpdateDepartmentGateway;
import com.confra.api.domain.DepartmentEntity;
import com.confra.api.exceptions.RequestNotAllowedException;
import com.confra.api.exceptions.RequiredObjectsIsNullException;
import com.confra.api.exceptions.ResourceNotFoundException;
import com.confra.api.infra.persistence.repositories.DepartmentRepository;
import com.confra.api.infra.persistence.tables.Department;

import java.util.Optional;

public class DepartmentRepositoryGateway implements
        CreateDepartmentGateway,
        FindDepartmentGateway,
        UpdateDepartmentGateway
{
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

    @Override
    public DepartmentEntity findDepartmentById(String name) {
        Optional<Department> findDepartment = departmentRepository.findByName(name);
        if (findDepartment.isEmpty()) {
            throw new ResourceNotFoundException("Department not found");
        }

        return departmentEntityMapper.toDomainObject(findDepartment.get());
    }

    @Override
    public DepartmentEntity updateDepartment(String name, DepartmentEntity newDepartment) {
        var findDepartment = departmentRepository.findByName(name)
                .orElseThrow(ResourceNotFoundException::new);

        findDepartment.setName(newDepartment.getName());
        findDepartment.setDescription(newDepartment.getDescription());
        findDepartment.setSupervisor(newDepartment.getSupervisor());

        Department updatedDepartment = departmentRepository.save(findDepartment);

        return departmentEntityMapper.toDomainObject(updatedDepartment);
    }
}
