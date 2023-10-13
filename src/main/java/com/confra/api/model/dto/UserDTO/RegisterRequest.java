package com.confra.api.model.dto.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Name is mandatory")
    private String descName;

    @NotNull
    private Date dtRegistration;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Department is mandatory")
    private String descDepartment;

    @NotNull
    private Integer totalInstallments;

    @NotNull
    private Integer totalInstallmentsPaid;

    private byte[] base64QRCode;
}
