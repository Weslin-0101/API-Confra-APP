package com.confra.api.main.controllers.user;

import com.confra.api.application.useCases.user.CreateUserInteractor;
import com.confra.api.domain.UserEntity;
import com.confra.api.main.config.authentication.JwtAuthenticationFilter;
import com.confra.api.main.controllers.dtos.user.RegisterRequestDTO;
import com.confra.api.main.controllers.dtos.user.RegisterResponseDTO;
import com.confra.api.main.controllers.dtos.user.UserDTOMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CreateUserController.class)
class CreateUserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CreateUserInteractor createUserUsecase;
    @MockBean
    private UserDTOMapper userDTOMapper;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void shouldRequestUserCreation() throws Exception {
        RegisterRequestDTO request = new RegisterRequestDTO();
        UserEntity createdUSer = new UserEntity();

        Mockito.when(userDTOMapper.toUser(request)).thenReturn(new UserEntity());
        Mockito.when(createUserUsecase.createUser(new UserEntity())).thenReturn(createdUSer);
        Mockito.when(userDTOMapper.toRegisterResponse(createdUSer)).thenReturn(new RegisterResponseDTO());

        mockMvc.perform(MockMvcRequestBuilders.post("/confra/api/v1/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}