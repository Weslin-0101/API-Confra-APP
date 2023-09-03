package com.confra.api.controllers;

import com.confra.api.model.User;
import com.confra.api.qrcode.MethodUtils;
import com.confra.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("root/confra/api/v1/qrcode")
@AllArgsConstructor
public class QRCodeController {

    private final UserService userService;

    @GetMapping("/generateByte/{id}")
    public ResponseEntity<User> generateByteQRCode(@PathVariable(value = "id") UUID id) {
        User userEntity = null;
        User user = userService.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        } else {
            userEntity = user;
            userEntity.setBase64QRCode(MethodUtils.generateByteQRCode(userEntity.getEmail(), 250, 250));
        }

        return ResponseEntity.status(HttpStatus.OK).body(userEntity);
    }

    @GetMapping("/generateQRCode/{id}")
    public ResponseEntity<User> generateImageQRCode(@PathVariable(value = "id") UUID id) {
        String imagePath = "./src/main/qrcode/images/QRCode.png";
        User userEntity = null;
        User user = userService.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        } else {
            userEntity = user;
            MethodUtils.generateImageQRCode(userEntity.getEmail(), 250, 250, imagePath);
        }

        return ResponseEntity.status(HttpStatus.OK).body(userEntity);
    }
}
