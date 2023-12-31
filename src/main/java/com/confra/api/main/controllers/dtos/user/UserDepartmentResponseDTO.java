package com.confra.api.main.controllers.dtos.user;

import lombok.Data;

@Data
public class UserDepartmentResponseDTO {
    private Long randomNumber;
    private String descName;
    private String descDepartment;

    public UserDepartmentResponseDTO(Long randomNumber, String descName, String descDepartment) {
        this.randomNumber = randomNumber;
        this.descName = descName;
        this.descDepartment = descDepartment;
    }
}
