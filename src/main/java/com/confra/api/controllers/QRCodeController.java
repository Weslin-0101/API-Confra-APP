package com.confra.api.controllers;

import com.confra.api.docs.schemas.InternalServerErrorSchema;
import com.confra.api.docs.schemas.NotFoundSchema;
import com.confra.api.docs.schemas.UnauthorizedSchema;
import com.confra.api.model.User;
import com.confra.api.qrcode.MethodUtils;
import com.confra.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Generate BarCode",
            description = "Returns a QRCode Barcode in Base64. You'll need to get the BarCode and decode it",
            tags = { "QRCode" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class)) }),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = { @Content(schema = @Schema(implementation = NotFoundSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<User> generateByteQRCode(@PathVariable(value = "id") UUID id) {
        User userEntity = userService.findById(id);

        if (userEntity == null) {
            throw new RuntimeException("User not found");
        }
        String emailAndId = userEntity.getEmail() + " - " + id.toString();
        byte[] qrCode = MethodUtils.generateByteQRCode(emailAndId, 250, 250);
        userEntity.setBase64QRCode(qrCode);
        User updatedUser = userService.updateBarcodeUser(id, qrCode);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @GetMapping("/generateQRCode/{id}")
    @Operation(
            summary = "Returns a QRCode Image",
            description = "Returns a QRCode Image to validity",
            tags = { "QRCode" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = { @Content()}),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedSchema.class)) }),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = { @Content(schema = @Schema(implementation = NotFoundSchema.class)) }),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = { @Content(schema = @Schema(implementation = InternalServerErrorSchema.class)) })
            }
    )
    public ResponseEntity<User> generateImageQRCode(@PathVariable(value = "id") UUID id) {
        String imagePath = "./src/main/java/com/confra/api/qrcode/QRCode.png";
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
