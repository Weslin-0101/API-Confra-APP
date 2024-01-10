package com.confra.api.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DepartmentEntity {
    private String name;
    private String description;
    private String supervisor;
    private Date dtRegistration;

    public DepartmentEntity() {}

    public DepartmentEntity(String name, String description, String supervisor, Date dtRegistration) {
        this.name = name;
        this.description = description;
        this.supervisor = supervisor;
        this.dtRegistration = dtRegistration;
    }
}
