package com.confra.api.main.config.user;

import com.confra.api.application.gateways.department.CreateDepartmentGateway;
import com.confra.api.application.gateways.department.DeleteDepartmentGateway;
import com.confra.api.application.gateways.department.FindDepartmentGateway;
import com.confra.api.application.gateways.department.UpdateDepartmentGateway;
import com.confra.api.application.useCases.department.CreateDepartmentInteractor;
import com.confra.api.application.useCases.department.DeleteDepartmentInteractor;
import com.confra.api.application.useCases.department.FindDepartmentInteractor;
import com.confra.api.application.useCases.department.UpdateDepartmentInteractor;
import com.confra.api.infra.gateways.departmentGateway.DepartmentEntityMapper;
import com.confra.api.infra.gateways.departmentGateway.DepartmentRepositoryGateway;
import com.confra.api.infra.persistence.repositories.DepartmentRepository;
import com.confra.api.main.controllers.dtos.department.DepartmentDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartmentConfig {
    @Bean
    CreateDepartmentInteractor createDepartmentInteractor (CreateDepartmentGateway createDepartmentGateway) {
        return new CreateDepartmentInteractor(createDepartmentGateway);
    }

    @Bean
    FindDepartmentInteractor findDepartmentInteractor (FindDepartmentGateway findDepartmentGateway) {
        return new FindDepartmentInteractor(findDepartmentGateway);
    }

    @Bean
    UpdateDepartmentInteractor updateDepartmentInteractor (UpdateDepartmentGateway updateDepartmentGateway) {
        return new UpdateDepartmentInteractor(updateDepartmentGateway);
    }

    @Bean
    DeleteDepartmentInteractor deleteDepartmentInteractor (DeleteDepartmentGateway deleteDepartmentGateway) {
        return new DeleteDepartmentInteractor(deleteDepartmentGateway);
    }

    @Bean
    CreateDepartmentGateway createDepartmentGateway (
            DepartmentRepository departmentRepository,
            DepartmentEntityMapper departmentEntityMapper
    ) {
        return new DepartmentRepositoryGateway(departmentRepository, departmentEntityMapper);
    }

    @Bean
    FindDepartmentGateway findDepartmentGateway (
            DepartmentRepository departmentRepository,
            DepartmentEntityMapper departmentEntityMapper
    ) {
        return new DepartmentRepositoryGateway(departmentRepository, departmentEntityMapper);
    }

    @Bean
    UpdateDepartmentGateway updateDepartmentGateway(
            DepartmentRepository departmentRepository,
            DepartmentEntityMapper departmentEntityMapper
    ) {
        return new DepartmentRepositoryGateway(departmentRepository, departmentEntityMapper);
    }

    @Bean
    DeleteDepartmentGateway deleteDepartmentGateway(
            DepartmentRepository departmentRepository,
            DepartmentEntityMapper departmentEntityMapper
    ) {
        return new DepartmentRepositoryGateway(departmentRepository, departmentEntityMapper);
    }

    @Bean
    DepartmentEntityMapper departmentEntityMapper() {
        return new DepartmentEntityMapper();
    }

    @Bean
    DepartmentDTOMapper departmentDTOMapper() {
        return new DepartmentDTOMapper();
    }
}
