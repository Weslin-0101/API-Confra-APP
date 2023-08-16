package com.confra.api.service;

import com.confra.api.model.User;
import com.confra.api.model.dto.UserDTO.RegisterRequest;
import com.confra.api.model.dto.UserDTO.RegisterResponse;
import com.confra.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse createAccount(RegisterRequest request) {
        var user = User.builder()
                .descName(request.getDescName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .descDepartment(request.getDescDepartment())
                .totalInstallments(request.getTotalInstallments())
                .totalInstallmentsPaid(request.getTotalInstallmentsPaid())
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
                .id(user.getId())
                .dtRegistration(new Date())
                .email(user.getEmail())
                .password(user.getPassword())
                .descDepartment(user.getDescDepartment())
                .totalInstallments(user.getTotalInstallments())
                .totalInstallmentsPaid(user.getTotalInstallmentsPaid())
                .build();
    }
}
