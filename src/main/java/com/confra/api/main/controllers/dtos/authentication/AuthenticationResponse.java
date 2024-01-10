package com.confra.api.main.controllers.dtos.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String name;
    private String accessToken;
//    @JsonProperty("refresh_token")
//    private String refreshToken;
}
