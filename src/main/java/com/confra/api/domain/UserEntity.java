package com.confra.api.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserEntity {
    private UUID id;
    private String cpf;
    private Date dtRegistration;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Integer totalInstallments;
    private Integer totalInstallmentsPaid;
    private String verificationCode;
    private Boolean verified;

    public UserEntity() {}

    public UserEntity(
            String cpf,
            Date dtRegistration,
            String name,
            String lastname,
            String email,
            String password,
            Integer totalInstallments,
            Integer totalInstallmentsPaid,
            String verificationCode,
            Boolean verified
    ) {
        this.cpf = cpf;
        this.dtRegistration = dtRegistration;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.totalInstallments = totalInstallments;
        this.totalInstallmentsPaid = totalInstallmentsPaid;
        this.verificationCode = verificationCode;
        this.verified = verified;
    }
}
