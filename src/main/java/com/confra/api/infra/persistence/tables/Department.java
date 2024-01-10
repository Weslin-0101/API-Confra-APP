package com.confra.api.infra.persistence.tables;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Entity
@Table(name = "_department")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDepartment;
    @Column(length = 100, unique = true, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String description;
    @Column(length = 50, nullable = false)
    private String supervisor;
    private Date dtRegistration;
    private Date dtUpdate;

    public Department(
        Long idDepartment,
        String name,
        String description,
        String supervisor,
        Date dtRegistration,
        Date dtUpdate
    ) {
        this.idDepartment = idDepartment;
        this.name = name;
        this.description = description;
        this.supervisor = supervisor;
        this.dtRegistration = dtRegistration;
        this.dtUpdate = dtUpdate;
    }

    public Department() {

    }
}
