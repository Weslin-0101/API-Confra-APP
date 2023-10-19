package com.confra.api.service;

import com.confra.api.model.Role;
import com.confra.api.model.User;
import com.confra.api.model.dto.UserDTO.RegisterRequest;
import com.confra.api.model.dto.UserDTO.RegisterResponse;
import com.confra.api.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse createAccount(RegisterRequest request, Boolean isAdmin) {
        Role userRole = isAdmin ? Role.ADMIN : Role.USER;

        var user = User.builder()
                .descName(request.getDescName())
                .codDocument(request.getCodDocument())
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
                .descName(user.getDescName())
                .codDocument(user.getCodDocument())
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
                .descName(request.getDescName())
                .codDocument(request.getCodDocument())
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
                .descName(user.getDescName())
                .codDocument(user.getCodDocument())
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
        entity.setDescName(request.getDescName());
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
