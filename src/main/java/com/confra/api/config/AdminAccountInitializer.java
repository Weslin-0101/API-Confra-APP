package com.confra.api.config;

import com.confra.api.model.dto.UserDTO.RegisterRequest;
import com.confra.api.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AdminAccountInitializer implements CommandLineRunner {

    private final UserService userService;

    public AdminAccountInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        RegisterRequest adminRequest = new RegisterRequest();
        adminRequest.setDescName("admin");
        adminRequest.setCodDocument("83682823069");
        adminRequest.setDtRegistration(new Date());
        adminRequest.setEmail("admin@gmail.com");
        adminRequest.setPassword("admin123");
        adminRequest.setDescDepartment("API");
        adminRequest.setTotalInstallments(4);
        adminRequest.setTotalInstallmentsPaid(4);

        userService.createAdminAccount(adminRequest);
    }
}
