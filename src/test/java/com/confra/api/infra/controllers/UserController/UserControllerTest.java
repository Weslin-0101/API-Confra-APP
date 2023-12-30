package com.confra.api.infra.controllers.UserController;

import com.confra.api.application.useCases.CreateUserInteractor;
import com.confra.api.domain.UserEntity;
import com.confra.api.infra.controllers.dtos.user.RegisterRequestDTO;
import com.confra.api.infra.controllers.dtos.user.RegisterResponseDTO;
import com.confra.api.infra.controllers.dtos.user.UserDTOMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private CreateUserInteractor createUserUsecase;
    @Mock
    private UserDTOMapper userDTOMapper;
    @InjectMocks
    private UserController userController;

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