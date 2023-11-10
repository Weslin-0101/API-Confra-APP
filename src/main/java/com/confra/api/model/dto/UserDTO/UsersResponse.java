package com.confra.api.model.dto.UserDTO;

import lombok.Data;

@Data
public class UsersResponse {
    private Long randomNumber;
    private String descName;
    private String descDepartment;

    public UsersResponse(Long randomNumber, String descName, String descDepartment) {
        this.randomNumber = randomNumber;
        this.descName = descName;
        this.descDepartment = descDepartment;
    }
}
