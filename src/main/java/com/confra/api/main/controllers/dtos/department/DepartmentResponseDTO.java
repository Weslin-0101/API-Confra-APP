package com.confra.api.main.controllers.dtos.department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String supervisor;
    private Date dtRegistration;
    private Date dtUpdate;
}
