package com.confra.api.model.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String descName;
    private String email;
    private String password;
    private String descDepartment;
    private Integer totalInstallments;
    private Integer totalInstallmentsPaid;
}
