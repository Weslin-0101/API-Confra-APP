package com.confra.api.controllers;

import com.confra.api.model.dto.UserDTO.RegisterRequest;
import com.confra.api.model.dto.UserDTO.RegisterResponse;
import com.confra.api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "root/confra/api/v1"
)
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> create (@RequestBody @Valid RegisterRequest request) throws Exception {
        return ResponseEntity.ok(userService.createAccount(request));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello World");

    }
}
