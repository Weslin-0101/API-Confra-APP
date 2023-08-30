package com.confra.api.service;

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
    public List<User>findAll(){
        return userRepository.findAll();
    }
    public User findById(UUID id){ return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));}

    public User save(User user) { return userRepository.save(user); }

    public void delete(UUID id) {
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não encontrado"));

        userRepository.delete(entity);
    }
}
