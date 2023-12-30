package com.confra.api.infra.controllers.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {
    private UUID id;
    private Date dtRegistration;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Integer totalInstallments;
    private Integer totalInstallmentsPaid;
}
