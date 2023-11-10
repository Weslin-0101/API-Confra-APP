package com.confra.api.service;

import com.confra.api.exceptions.NotAllowedDuplicateValueException;
import com.confra.api.exceptions.RequestNotAllowedException;
import com.confra.api.exceptions.ResourceNotFoundException;
import com.confra.api.model.Role;
import com.confra.api.model.User;
import com.confra.api.model.dto.UserDTO.RegisterRequest;
import com.confra.api.model.dto.UserDTO.RegisterResponse;
import com.confra.api.qrcode.MethodUtils;
import com.confra.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
        Boolean checkIn = false;

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new NotAllowedDuplicateValueException();
        }

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
                .checkIn(checkIn)
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
                .checkIn(user.getCheckIn())
                .build();
    }

    public List<User>findAll(){
        return userRepository.findAll();
    }

    public User updateUser(String email, RegisterRequest request) {
        var entity = userRepository.findByEmail(email)
                .orElseThrow(ResourceNotFoundException::new);
        entity.setDescName(request.getDescName());
        entity.setDtRegistration(request.getDtRegistration());
        entity.setEmail(request.getEmail());
        entity.setPassword(request.getPassword());
        entity.setDescDepartment(request.getDescDepartment());
        entity.setTotalInstallments(request.getTotalInstallments());
        entity.setTotalInstallmentsPaid(request.getTotalInstallmentsPaid());

        return entity;
    }

    public RegisterResponse updateBase64User(UUID id) {
        var entity = userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        if (entity.getCheckIn()) {
            throw new RequestNotAllowedException();
        }

        long randomNumber = userRepository.count();
        String emailAndId = entity.getEmail() + " - " + id.toString();
        byte[] qrCode = MethodUtils.generateByteQRCode(emailAndId, 250, 250);
        entity.setBase64QRCode(qrCode);
        entity.setCheckIn(true);
        entity.setRandomNumber(randomNumber + 1);

        userRepository.save(entity);

        return RegisterResponse.builder()
                .id(id)
                .descName(entity.getDescName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .role(entity.getRole())
                .base64QRCode(entity.getBase64QRCode())
                .checkIn(entity.getCheckIn())
                .randomNumber(entity.getRandomNumber())
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

    public void deleteALlUsers() {
        var entity = userRepository.findAll();

        userRepository.deleteAll(entity);
    }
}
