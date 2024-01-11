package com.confra.api.main.controllers.dtos.department;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestDTO {
    @NotEmpty(message = "Name is mandatory")
    @NotNull
    private String name;
    @NotNull
    @NotEmpty(message = "Description is mandatory")
    private String description;
    @NotNull
    @NotEmpty(message = "Supervisor is mandatory")
    private String supervisor;
}
