package com.confra.api.main.controllers.user;

import com.confra.api.application.useCases.user.DeleteUserByEmailInteractor;
import com.confra.api.main.docs.schemas.BadRequestSchema;
import com.confra.api.main.docs.schemas.InternalServerErrorSchema;
import com.confra.api.main.docs.schemas.NotFoundSchema;
import com.confra.api.main.docs.schemas.UnauthorizedSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "confra/api/v1/user")
public class DeleteUserByEmailController {
    private final DeleteUserByEmailInteractor deleteUserByEmailUseCase;

    public DeleteUserByEmailController(DeleteUserByEmailInteractor deleteUserByEmailUseCase) {
        this.deleteUserByEmailUseCase = deleteUserByEmailUseCase;
    }

    @DeleteMapping("{email}")
    @Operation(
            summary = "Delete a account",
            description = "Delete a entity Account by email",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content(schema = @Schema(implementation = BadRequestSchema.class)) }),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = { @Content(schema = @Schema(implementation = NotFoundSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<?> deleteUserByEmail(@PathVariable(value = "email") String email) {
        deleteUserByEmailUseCase.deleteUserByEmail(email);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
