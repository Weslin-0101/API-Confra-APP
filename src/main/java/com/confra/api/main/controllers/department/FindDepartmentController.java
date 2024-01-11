package com.confra.api.main.controllers.department;

import com.confra.api.application.useCases.department.FindDepartmentInteractor;
import com.confra.api.domain.DepartmentEntity;
import com.confra.api.main.controllers.dtos.department.DepartmentDTOMapper;
import com.confra.api.main.controllers.dtos.department.DepartmentResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "confra/api/v2/department")
public class FindDepartmentController {
    private final FindDepartmentInteractor findDepartmentInteractor;
    private final DepartmentDTOMapper departmentDTOMapper;

    public FindDepartmentController(FindDepartmentInteractor findDepartmentInteractor, DepartmentDTOMapper departmentDTOMapper) {
        this.findDepartmentInteractor = findDepartmentInteractor;
        this.departmentDTOMapper = departmentDTOMapper;
    }

    @GetMapping("/{name}")
    public ResponseEntity<DepartmentResponseDTO> findDepartment(@PathVariable(value = "name") String name) {
        DepartmentEntity department = findDepartmentInteractor.findDepartment(name);
        DepartmentResponseDTO response = departmentDTOMapper.toRegisterResponse(department);

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
