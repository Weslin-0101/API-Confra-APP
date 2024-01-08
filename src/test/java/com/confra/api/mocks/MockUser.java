package com.confra.api.mocks;

import com.confra.api.domain.UserEntity;
import com.confra.api.infra.persistence.tables.User;

import java.util.Date;
import java.util.UUID;

public class MockUser {
    public UserEntity mockUserEntity() {
        UserEntity userEntity = new UserEntity(
                "11111111111",
                new Date(),
                "Teste",
                "User",
                "teste@gmail.com",
                "123456",
                4,
                4
        );

        return userEntity;
    }

    public User mockUserPersistence() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setCpf("79933118706");
        user.setDtRegistration(new Date());
        user.setName("Teste User");
        user.setLastname("Persistence");
        user.setEmail("userpersistence@gmail.com");
        user.setPassword("123456");
        user.setTotalInstallments(4);
        user.setTotalInstallmentsPaid(4);
        user.setBase64QRCode(null);
        user.setCheckIn(false);

        return user;
    }
}
