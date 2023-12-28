package com.confra.api.mocks;

import com.confra.api.domain.UserEntity;

import java.util.Date;

public class MockUser {
    public UserEntity mockUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setCpf("11111111111");
        userEntity.setDtRegistration(new Date());
        userEntity.setName("TesteUser");
        userEntity.setLastname("2");
        userEntity.setEmail("teste@gmail.com");
        userEntity.setPassword("123456");
        userEntity.setTotalInstallments(4);
        userEntity.setTotalInstallmentsPaid(4);

        return userEntity;
    }
}
