package com.confra.api.service;

import com.confra.api.model.Role;
import com.confra.api.infra.persistence.tables.User;
import com.confra.api.model.dto.UserDTO.RegisterRequest;
import com.confra.api.model.dto.UserDTO.RegisterResponse;
import com.confra.api.infra.persistence.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse createAccount(RegisterRequest request, Boolean isAdmin) {
        Role userRole = isAdmin ? Role.ADMIN : Role.USER;

        var user = User.builder()
                .name(request.getDescName())
                .lastname(request.getDescName())
                .cpf(request.getCodDocument())
                .dtRegistration(request.getDtRegistration())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(userRole)
                .descDepartment(request.getDescDepartment())
                .totalInstallments(request.getTotalInstallments())
                .totalInstallmentsPaid(request.getTotalInstallmentsPaid())
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
                .id(user.getId())
                .dtRegistration(user.getDtRegistration())
                .descName(user.getName())
                .codDocument(user.getCpf())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .descDepartment(user.getDescDepartment())
                .totalInstallments(user.getTotalInstallments())
                .totalInstallmentsPaid(user.getTotalInstallmentsPaid())
                .base64QRCode(user.getBase64QRCode())
                .build();
    }

    public RegisterResponse createAdminAccount(RegisterRequest request) {
        var user = User.builder()
                .name(request.getDescName())
                .cpf(request.getCodDocument())
                .dtRegistration(request.getDtRegistration())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .descDepartment(request.getDescDepartment())
                .totalInstallments(request.getTotalInstallments())
                .totalInstallmentsPaid(request.getTotalInstallmentsPaid())
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
                .id(user.getId())
                .dtRegistration(user.getDtRegistration())
                .descName(user.getName())
                .codDocument(user.getCpf())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .descDepartment(user.getDescDepartment())
                .totalInstallments(user.getTotalInstallments())
                .totalInstallmentsPaid(user.getTotalInstallmentsPaid())
                .base64QRCode(user.getBase64QRCode())
                .build();
    }
    public List<User>findAll(){
        return userRepository.findAll();
    }

    public User updateUser(String email, RegisterRequest request) {
        var entity = userRepository.findByEmail(email)
                .orElseThrow();
        entity.setName(request.getDescName());
        entity.setDtRegistration(request.getDtRegistration());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setDescDepartment(request.getDescDepartment());
        entity.setTotalInstallments(request.getTotalInstallments());
        entity.setTotalInstallmentsPaid(request.getTotalInstallmentsPaid());

        return entity;
    }

    public User updateBarcodeUser(UUID id, byte[] barcode) {
        var entity = userRepository.findById(id)
                .orElseThrow();
        entity.setBase64QRCode(barcode);

        return entity;
    }

    public User findById(UUID id){ return userRepository.findById(id).orElseThrow(() -> new RuntimeException("No records found for this ID"));}

    public void delete(UUID id) {
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No record found for this ID"));

        userRepository.delete(entity);
    }
}
