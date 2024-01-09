package com.confra.api.main.controllers.user;

import com.confra.api.application.useCases.user.CreateUserInteractor;
import com.confra.api.domain.UserEntity;
import com.confra.api.infra.persistence.tables.User;
import com.confra.api.main.controllers.dtos.user.RegisterRequestDTO;
import com.confra.api.main.controllers.dtos.user.RegisterResponseDTO;
import com.confra.api.main.controllers.dtos.user.UserDTOMapper;
import com.confra.api.main.docs.schemas.BadRequestSchema;
import com.confra.api.main.docs.schemas.InternalServerErrorSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
public class CreateUserController {
    private final CreateUserInteractor createUserUsecase;
    private final UserDTOMapper userDTOMapper;

    @PostMapping("/create")
    @Operation(
            summary = "Create a new User Account",
            description = "Returns a User account entity",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class)) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content(schema = @Schema(implementation = BadRequestSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<RegisterResponseDTO> createUser(@RequestBody @Valid RegisterRequestDTO request) {
        UserEntity user = userDTOMapper.toUser(request);
        UserEntity createdUser = createUserUsecase.createUser(user);
        RegisterResponseDTO response = userDTOMapper.toRegisterResponse(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
