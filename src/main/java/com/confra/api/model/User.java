package com.confra.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codDocument;
    private Date dtRegistration;
    private String descName;
    private String email;
    private String descDepartment;
    private Integer totalInstallments;
    private Integer totalInstallmentsPaid;

    public User() {}

    public String getCodDocument() {
        return codDocument;
    }

    public void setCodDocument(String codDocument) {
        this.codDocument = codDocument;
    }

    public Date getDtRegistration() {
        return dtRegistration;
    }

    public void setDtRegistration(Date dtRegistration) {
        this.dtRegistration = dtRegistration;
    }

    public String getDescName() {
        return descName;
    }

    public void setDescName(String descName) {
        this.descName = descName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescDepartment() {
        return descDepartment;
    }

    public void setDescDepartment(String descDepartment) {
        this.descDepartment = descDepartment;
    }

    public Integer getTotalInstallments() {
        return totalInstallments;
    }

    public void setTotalInstallments(Integer totalInstallments) {
        this.totalInstallments = totalInstallments;
    }

    public Integer getTotalInstallmentsPaid() {
        return totalInstallmentsPaid;
    }

    public void setTotalInstallmentsPaid(Integer totalInstallmentsPaid) {
        this.totalInstallmentsPaid = totalInstallmentsPaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(codDocument, user.codDocument) && Objects.equals(dtRegistration, user.dtRegistration) && Objects.equals(descName, user.descName) && Objects.equals(email, user.email) && Objects.equals(descDepartment, user.descDepartment) && Objects.equals(totalInstallments, user.totalInstallments) && Objects.equals(totalInstallmentsPaid, user.totalInstallmentsPaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codDocument, dtRegistration, descName, email, descDepartment, totalInstallments, totalInstallmentsPaid);
    }
}
