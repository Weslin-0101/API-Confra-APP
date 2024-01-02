package com.confra.api.main.controllers;

import com.confra.api.main.controllers.dtos.user.RegisterRequestDTO;
import com.confra.api.main.controllers.dtos.user.RegisterResponseDTO;
import com.confra.api.main.docs.schemas.BadRequestSchema;
import com.confra.api.main.docs.schemas.InternalServerErrorSchema;
import com.confra.api.main.docs.schemas.NotFoundSchema;
import com.confra.api.main.docs.schemas.UnauthorizedSchema;
import com.confra.api.infra.persistence.tables.User;
import com.confra.api.main.controllers.dtos.user.UserDepartmentResponseDTO;
import com.confra.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@AllArgsConstructor
public class UserController {

    private final UserService userService;

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
        public ResponseEntity<RegisterResponseDTO> createUser(@RequestBody @Valid RegisterRequestDTO registerRequest) {
            var user =  userService.createAccount(registerRequest, false);
            BeanUtils.copyProperties(registerRequest, user);

            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }

    @PostMapping("/create-admin")
    @Operation(
            summary = "Create a new Admin Account",
            description = "Returns a Admin account entity",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class)) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content(schema = @Schema(implementation = BadRequestSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<RegisterResponseDTO> createAdmin(@RequestBody @Valid RegisterRequestDTO registerRequest) {
        var user = userService.createAccount(registerRequest, true);
        BeanUtils.copyProperties(registerRequest, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping()
    @Operation(
            summary = "Find all accounts",
            description = "Returns a list of entity Accounts",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class)) }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = { @Content(schema = @Schema(implementation = NotFoundSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<List<User>> findAll(){
        List<User> userList = userService.findAll();
        if (userList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        userList.forEach(account -> {
            try {
                account.add(linkTo(methodOn(UserController.class).findById(account.getId())).withSelfRel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Find account by ID UUID",
            description = "Returns a entity Account by ID UUID",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class)) }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = { @Content(schema = @Schema(implementation = NotFoundSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<User> findById(@PathVariable(value="id") UUID id){
        var user = userService.findById(id);
        user.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/sort")
    @Operation(
            summary = "Find all Members for the raffle",
            description = "Return all Users with your numberRandom, name and department information",
            tags = { "Account" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = UserDepartmentResponseDTO.class)) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content(schema = @Schema(implementation = BadRequestSchema.class)) }),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<List<UserDepartmentResponseDTO>> returnAll() {
        var user = userService.returnAll();
        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
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
    public ResponseEntity<User> updateUser (
            @PathVariable(value = "email") String email,
            @RequestBody @Valid RegisterRequestDTO registerRequest
    ){
        var userUpdated = userService.updateUser(email, registerRequest);
        userUpdated.add(linkTo(methodOn(UserController.class).updateUser(email, registerRequest)).withSelfRel());
        BeanUtils.copyProperties(registerRequest, userUpdated);

        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    @DeleteMapping("/{id}")
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
    public ResponseEntity<?> deleteUser (@PathVariable(value = "id") UUID id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
