package com.confra.api.main.controllers.user;

import com.confra.api.application.useCases.UpdateUserInteractor;
import com.confra.api.domain.UserEntity;
import com.confra.api.main.controllers.dtos.user.RegisterRequestDTO;
import com.confra.api.main.controllers.dtos.user.RegisterResponseDTO;
import com.confra.api.main.controllers.dtos.user.UserDTOMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "confra/api/v1/user")
public class UpdateUserController {
    private final UpdateUserInteractor updateUserUseCase;
    private final UserDTOMapper userDTOMapper;

    public UpdateUserController(UpdateUserInteractor updateUserUseCase, UserDTOMapper userDTOMapper) {
        this.updateUserUseCase = updateUserUseCase;
        this.userDTOMapper = userDTOMapper;
    }

    @PutMapping("/{email}")
    public ResponseEntity<RegisterResponseDTO> updateUser(
            @PathVariable(value = "email") String email,
            @RequestBody @Valid RegisterRequestDTO request
    ) {
        UserEntity user = userDTOMapper.toUser(request);
        UserEntity updatedUser = updateUserUseCase.updateUser(email, user);
        RegisterResponseDTO response = userDTOMapper.toRegisterResponse(updatedUser);

        return ResponseEntity.ok(response);
    }
}
