package com.confra.api.main.controllers.dtos.user;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterResponseDTO {
    private UUID id;
    private String cpf;
    private Date dtRegistration;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Integer totalInstallments;
    private Integer totalInstallmentsPaid;
    private byte[] base64QRCode;
    private Boolean checkIn;
}
