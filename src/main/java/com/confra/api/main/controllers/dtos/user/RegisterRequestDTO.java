package com.confra.api.main.controllers.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    @NotEmpty(message = "CPF is mandatory")
    @CPF
    private String cpf;
    @NotNull
    private Date dtRegistration;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotNull
    private Integer totalInstallments;
    @NotNull
    private Integer totalInstallmentsPaid;
    private byte[] base64QRCode;
    private Boolean checkIn;
}
