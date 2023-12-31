package com.confra.api.main.controllers.user;

import com.confra.api.application.useCases.CreateUserInteractor;
import com.confra.api.domain.UserEntity;
import com.confra.api.main.controllers.dtos.user.RegisterRequestDTO;
import com.confra.api.main.controllers.dtos.user.RegisterResponseDTO;
import com.confra.api.main.controllers.dtos.user.UserDTOMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "confra/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final CreateUserInteractor createUserUsecase;
    private final UserDTOMapper userDTOMapper;

    @PostMapping("/create")
    public ResponseEntity<RegisterResponseDTO> createUser(@RequestBody @Valid RegisterRequestDTO request) {
        UserEntity user = userDTOMapper.toUser(request);
        UserEntity createdUser = createUserUsecase.createUser(user);
        RegisterResponseDTO response = userDTOMapper.toRegisterResponse(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
