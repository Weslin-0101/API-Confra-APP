package com.confra.api.repositories;

import com.confra.api.model.User;
import com.confra.api.model.dto.UserDTO.UsersResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) from User u")
    Integer getMaxUsers();

    @Query("SELECT new com.confra.api.model.dto.UserDTO.UsersResponse(u.randomNumber, u.descName, u.descDepartment) FROM User u")
    List<UsersResponse> returnAllMembers();
}
