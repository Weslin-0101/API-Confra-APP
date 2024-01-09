package com.confra.api.main.controllers.user;

import com.confra.api.application.useCases.user.UpdateUserInteractor;
import com.confra.api.domain.UserEntity;
import com.confra.api.infra.persistence.tables.User;
import com.confra.api.main.controllers.dtos.user.RegisterRequestDTO;
import com.confra.api.main.controllers.dtos.user.RegisterResponseDTO;
import com.confra.api.main.controllers.dtos.user.UserDTOMapper;
import com.confra.api.main.docs.schemas.BadRequestSchema;
import com.confra.api.main.docs.schemas.InternalServerErrorSchema;
import com.confra.api.main.docs.schemas.UnauthorizedSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Update a account",
            description = "Returns a new entity Account after update",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class)) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content(schema = @Schema(implementation = BadRequestSchema.class)) }),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
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
