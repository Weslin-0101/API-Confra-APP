package com.confra.api.infra.controllers.dtos.user;

import com.confra.api.domain.UserEntity;

public class UserDTOMapper {
    public RegisterResponseDTO toRegisterResponse(UserEntity user) {
        return RegisterResponseDTO.builder()
                .dtRegistration(user.getDtRegistration())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .totalInstallments(user.getTotalInstallments())
                .totalInstallmentsPaid(user.getTotalInstallmentsPaid())
                .build();
    }

    public UserEntity toUser(RegisterRequestDTO request) {
        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setTotalInstallments(request.getTotalInstallments());
        user.setTotalInstallmentsPaid(request.getTotalInstallmentsPaid());

        return user;
    }
}
