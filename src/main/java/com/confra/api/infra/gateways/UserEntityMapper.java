package com.confra.api.infra.gateways;

import com.confra.api.domain.UserEntity;
import com.confra.api.infra.persistence.tables.User;

public class UserEntityMapper {
    User toEntity(UserEntity userDomain) {
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

    UserEntity toDomainObject(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setCpf(user.getCpf());
        userEntity.setDtRegistration(user.getDtRegistration());
        userEntity.setName(user.getName());
        userEntity.setLastname(user.getLastname());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setTotalInstallments(user.getTotalInstallments());
        userEntity.setTotalInstallmentsPaid(user.getTotalInstallmentsPaid());

        return userEntity;
    }
}
