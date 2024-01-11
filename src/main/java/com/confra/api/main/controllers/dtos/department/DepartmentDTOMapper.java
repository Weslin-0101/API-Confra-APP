package com.confra.api.main.controllers.dtos.department;

import com.confra.api.domain.DepartmentEntity;

public class DepartmentDTOMapper {
    public DepartmentResponseDTO toRegisterResponse (DepartmentEntity department) {
        return DepartmentResponseDTO.builder()
                .name(department.getName())
                .description(department.getDescription())
                .supervisor(department.getSupervisor())
                .build();
    }

    public DepartmentEntity toDepartment (DepartmentRequestDTO request) {
        DepartmentEntity department = new DepartmentEntity();
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setSupervisor(request.getSupervisor());

        return department;
    }
}
