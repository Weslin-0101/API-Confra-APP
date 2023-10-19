package com.confra.api.model.dto.UserDTO;

import com.confra.api.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Name is mandatory")
    private String descName;

    @NotEmpty(message = "Document Code is necessary (CPF)")
    @CPF
    private String codDocument;

    @NotNull
    private Date dtRegistration;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private Role role;

    @NotBlank(message = "Department is mandatory")
    private String descDepartment;

    @NotNull
    private Integer totalInstallments;

    @NotNull
    private Integer totalInstallmentsPaid;

    private byte[] base64QRCode;
}
