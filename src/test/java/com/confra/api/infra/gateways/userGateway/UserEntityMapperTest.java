package com.confra.api.infra.gateways.userGateway;

import com.confra.api.domain.UserEntity;
import com.confra.api.infra.gateways.userGateway.UserEntityMapper;
import com.confra.api.infra.persistence.tables.User;
import com.confra.api.mocks.MockUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityMapperTest {
    private MockUser mockUser = new MockUser();
    private UserEntityMapper mapper = new UserEntityMapper();

    @Test
    public void testToEntity() {
        UserEntity userEntity = mockUser.mockUserEntity();
        User user = mapper.toEntity(userEntity);

        assertEquals(userEntity.getCpf(), user.getCpf());
        assertEquals(userEntity.getDtRegistration(), user.getDtRegistration());
        assertEquals(userEntity.getName(), user.getName());
        assertEquals(userEntity.getLastname(), user.getLastname());
        assertEquals(userEntity.getEmail(), user.getEmail());
        assertEquals(userEntity.getPassword(), user.getPassword());
        assertEquals(userEntity.getTotalInstallments(), user.getTotalInstallments());
        assertEquals(userEntity.getTotalInstallmentsPaid(), user.getTotalInstallmentsPaid());
    }

    @Test
    public void shouldReturnAnUserEntityToDomainObject() {
        User user = mockUser.mockUserPersistence();
        UserEntity userEntity = mapper.toDomainObject(user);

        assertEquals(userEntity.getCpf(), user.getCpf());
        assertEquals(userEntity.getDtRegistration(), user.getDtRegistration());
        assertEquals(userEntity.getName(), user.getName());
        assertEquals(userEntity.getLastname(), user.getLastname());
        assertEquals(userEntity.getEmail(), user.getEmail());
        assertEquals(userEntity.getPassword(), user.getPassword());
        assertEquals(userEntity.getTotalInstallments(), user.getTotalInstallments());
        assertEquals(userEntity.getTotalInstallmentsPaid(), user.getTotalInstallmentsPaid());
    }
}