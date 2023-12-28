package com.confra.api.domain;

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
}
