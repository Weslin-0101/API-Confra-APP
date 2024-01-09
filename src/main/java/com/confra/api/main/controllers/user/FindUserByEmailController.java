package com.confra.api.main.controllers.user;

import com.confra.api.application.useCases.FindUserByEmailInteractor;
import com.confra.api.domain.UserEntity;
import com.confra.api.main.controllers.dtos.user.RegisterResponseDTO;
import com.confra.api.main.controllers.dtos.user.UserDTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "confra/api/v1/user")
@AllArgsConstructor
public class FindUserByEmailController {
    private final FindUserByEmailInteractor findUserByEmailUsecase;
    private final UserDTOMapper userDTOMapper;

    @GetMapping("/{email}")
    public ResponseEntity<RegisterResponseDTO> findUserByEmail(@PathVariable(value = "email") String email) {
        UserEntity user = findUserByEmailUsecase.findUserByEmail(email);
        RegisterResponseDTO response = userDTOMapper.toRegisterResponse(user);

        return ResponseEntity.ok(response);
    }
}
