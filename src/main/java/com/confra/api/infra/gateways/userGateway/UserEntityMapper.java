package com.confra.api.infra.gateways.userGateway;

import com.confra.api.domain.UserEntity;
import com.confra.api.infra.persistence.tables.User;

public class UserEntityMapper {
    public User toEntity(UserEntity userDomain) {
        User user = new User();
        user.setCpf(userDomain.getCpf());
        user.setDtRegistration(userDomain.getDtRegistration());
        user.setName(userDomain.getName());
        user.setLastname(userDomain.getLastname());
        user.setEmail(userDomain.getEmail());
        user.setPassword(userDomain.getPassword());
        user.setTotalInstallments(userDomain.getTotalInstallments());
        user.setTotalInstallmentsPaid(userDomain.getTotalInstallmentsPaid());

        return user;
    }

    public UserEntity toDomainObject(User user) {
        UserEntity userDomain = new UserEntity();
        userDomain.setCpf(user.getCpf());
        userDomain.setDtRegistration(user.getDtRegistration());
        userDomain.setName(user.getName());
        userDomain.setLastname(user.getLastname());
        userDomain.setEmail(user.getEmail());
        userDomain.setPassword(user.getPassword());
        userDomain.setTotalInstallments(user.getTotalInstallments());
        userDomain.setTotalInstallmentsPaid(user.getTotalInstallmentsPaid());

        return userDomain;
    }
}
