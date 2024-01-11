package com.confra.api.main.controllers.department;

import com.confra.api.application.useCases.department.UpdateDepartmentInteractor;
import com.confra.api.domain.DepartmentEntity;
import com.confra.api.main.controllers.dtos.department.DepartmentDTOMapper;
import com.confra.api.main.controllers.dtos.department.DepartmentRequestDTO;
import com.confra.api.main.controllers.dtos.department.DepartmentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "confra/api/v1/department")
public class UpdateDepartmentController {
    private final UpdateDepartmentInteractor updateDepartmentUseCase;
    private final DepartmentDTOMapper departmentDTOMapper;

    public UpdateDepartmentController(UpdateDepartmentInteractor updateDepartmentUseCase, DepartmentDTOMapper departmentDTOMapper) {
        this.updateDepartmentUseCase = updateDepartmentUseCase;
        this.departmentDTOMapper = departmentDTOMapper;
    }

    @PutMapping("/{name}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(
            @PathVariable(value = "name") String name,
            @RequestBody @Valid DepartmentRequestDTO request
            ) {
        DepartmentEntity departmentEntity = departmentDTOMapper.toDepartment(request);
        DepartmentEntity updatedDepartment = updateDepartmentUseCase.updateDepartment(name, departmentEntity);
        DepartmentResponseDTO response = departmentDTOMapper.toRegisterResponse(updatedDepartment);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
