package com.confra.api.main.controllers.user;

import com.confra.api.application.useCases.FindUserByEmailInteractor;
import com.confra.api.domain.UserEntity;
import com.confra.api.infra.persistence.tables.User;
import com.confra.api.main.controllers.dtos.user.RegisterResponseDTO;
import com.confra.api.main.controllers.dtos.user.UserDTOMapper;
import com.confra.api.main.docs.schemas.InternalServerErrorSchema;
import com.confra.api.main.docs.schemas.NotFoundSchema;
import com.confra.api.main.docs.schemas.UnauthorizedSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Find account by e-mail",
            description = "Returns a entity Account by e-mail",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class)) }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = { @Content(schema = @Schema(implementation = NotFoundSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<RegisterResponseDTO> findUserByEmail(@PathVariable(value = "email") String email) {
        UserEntity user = findUserByEmailUsecase.findUserByEmail(email);
        RegisterResponseDTO response = userDTOMapper.toRegisterResponse(user);

        return ResponseEntity.ok(response);
    }
}
