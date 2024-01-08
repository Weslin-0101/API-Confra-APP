package com.confra.api.infra.persistence.repositories;

import com.confra.api.infra.persistence.tables.User;
import com.confra.api.main.controllers.dtos.user.UserDepartmentResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) from User u")
    Integer getMaxUsers();
}
