package com.confra.api.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class UserEntity {
    private String cpf;
    private Date dtRegistration;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private Integer totalInstallments;
    private Integer totalInstallmentsPaid;

    public UserEntity() {}

    public UserEntity(String cpf, Date dtRegistration, String name, String lastname, String email, String password, Integer totalInstallments, Integer totalInstallmentsPaid) {
        this.cpf = cpf;
        this.dtRegistration = dtRegistration;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.totalInstallments = totalInstallments;
        this.totalInstallmentsPaid = totalInstallmentsPaid;
    }
}
