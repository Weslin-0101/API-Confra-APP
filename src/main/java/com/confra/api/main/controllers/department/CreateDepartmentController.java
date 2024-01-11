package com.confra.api.main.controllers.department;

import com.confra.api.application.useCases.department.CreateDepartmentInteractor;
import com.confra.api.domain.DepartmentEntity;
import com.confra.api.main.controllers.dtos.department.DepartmentDTOMapper;
import com.confra.api.main.controllers.dtos.department.DepartmentRequestDTO;
import com.confra.api.main.controllers.dtos.department.DepartmentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "confra/api/v2/department")
public class CreateDepartmentController {
    private final CreateDepartmentInteractor createDepartmentInteractor;
    private final DepartmentDTOMapper departmentDTOMapper;

    public CreateDepartmentController(CreateDepartmentInteractor createDepartmentInteractor, DepartmentDTOMapper departmentDTOMapper) {
        this.createDepartmentInteractor = createDepartmentInteractor;
        this.departmentDTOMapper = departmentDTOMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@RequestBody @Valid DepartmentRequestDTO request) {
        DepartmentEntity department = departmentDTOMapper.toDepartment(request);
        DepartmentEntity createdDepartment = createDepartmentInteractor.createDepartment(department);
        DepartmentResponseDTO response = departmentDTOMapper.toRegisterResponse(createdDepartment);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
