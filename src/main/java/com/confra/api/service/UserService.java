package com.confra.api.service;

import com.confra.api.main.controllers.dtos.user.RegisterRequestDTO;
import com.confra.api.main.controllers.dtos.user.RegisterResponseDTO;
import com.confra.api.main.exceptions.NotAllowedDuplicateValueException;
import com.confra.api.main.exceptions.RequestNotAllowedException;
import com.confra.api.main.exceptions.ResourceNotFoundException;
import com.confra.api.infra.persistence.repositories.UserRepository;
import com.confra.api.infra.persistence.tables.User;
import com.confra.api.domain.Role;
import com.confra.api.main.controllers.dtos.user.UserDepartmentResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private Set<Long> usedRandomNumbers = new HashSet<>();

    public RegisterResponseDTO createAccount(RegisterRequestDTO request, Boolean isAdmin) {
        Role userRole = isAdmin ? Role.ADMIN : Role.USER;
        Boolean checkIn = false;

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new NotAllowedDuplicateValueException();
        }

        var user = User.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .dtRegistration(request.getDtRegistration())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .totalInstallments(request.getTotalInstallments())
                .totalInstallmentsPaid(request.getTotalInstallmentsPaid())
                .checkIn(checkIn)
                .build();

        userRepository.save(user);

        return RegisterResponseDTO.builder()
                .id(user.getId())
                .dtRegistration(user.getDtRegistration())
                .name(user.getName())
                .cpf(user.getCpf())
                .email(user.getEmail())
                .password(user.getPassword())
                .totalInstallments(user.getTotalInstallments())
                .totalInstallmentsPaid(user.getTotalInstallmentsPaid())
                .base64QRCode(user.getBase64QRCode())
                .checkIn(user.getCheckIn())
                .build();
    }

    public List<User>findAll(){
        return userRepository.findAll();
    }

    public List<UserDepartmentResponseDTO> returnAll() {
        return userRepository.returnAllMembers();
    }

    public User updateUser(String email, RegisterRequestDTO request) {
        var entity = userRepository.findByEmail(email)
                .orElseThrow(ResourceNotFoundException::new);
        entity.setName(request.getName());
        entity.setDtRegistration(request.getDtRegistration());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setTotalInstallments(request.getTotalInstallments());
        entity.setTotalInstallmentsPaid(request.getTotalInstallmentsPaid());

        return entity;
    }

    public RegisterResponseDTO updateBase64User(UUID id) {
        var entity = userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        if (entity.getCheckIn()) {
            throw new RequestNotAllowedException();
        }

        long randomNumber = userRepository.getMaxUsers();
        long uniqueRandomNumber = generateUniqueRandomNumber(randomNumber);
//        String emailAndId = entity.getEmail() + " - " + id.toString();
//        byte[] qrCode = MethodUtils.generateByteQRCode(emailAndId, 250, 250);
//        entity.setBase64QRCode(qrCode);
        entity.setCheckIn(true);

        userRepository.save(entity);

        return RegisterResponseDTO.builder()
                .id(id)
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
//                .base64QRCode(entity.getBase64QRCode())
                .checkIn(entity.getCheckIn())
                .build();
    }

    public User findById(UUID id){
        return userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
    }

    public void delete(UUID id) {
        var entity = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        userRepository.delete(entity);
    }

    private long generateUniqueRandomNumber(long maxNumber) {
        long random;
        do {
            random = (long) (Math.random() * maxNumber + 1);
        } while (usedRandomNumbers.contains(random));

        usedRandomNumbers.add(random);
        return random;
    }
}
