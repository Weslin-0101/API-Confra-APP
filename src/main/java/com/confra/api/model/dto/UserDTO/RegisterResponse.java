package com.confra.api.model.dto.UserDTO;

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
public class RegisterResponse {
    private UUID id;
    private Date dtRegistration;
    private String descName;
    private String email;
    private String password;
    private String descDepartment;
    private Integer totalInstallments;
    private Integer totalInstallmentsPaid;
    private byte[] base64QRCode;
}
