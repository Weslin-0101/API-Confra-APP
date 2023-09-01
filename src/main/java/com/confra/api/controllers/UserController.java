package com.confra.api.controllers;

import com.confra.api.model.User;
import com.confra.api.model.dto.UserDTO.RegisterRequest;
import com.confra.api.model.dto.UserDTO.RegisterResponse;
import com.confra.api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "root/confra/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/create")
    public ResponseEntity<RegisterResponse> saveProduct(@RequestBody @Valid RegisterRequest registerRequest) {
        var user = userService.createAccount(registerRequest);
        BeanUtils.copyProperties(registerRequest, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAccount(registerRequest));
    }

    @GetMapping()
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
    public ResponseEntity<User> findById(@PathVariable(value="id") UUID id){
        var user = userService.findById(id);
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        user.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser (
            @PathVariable(value = "email") String email,
            @RequestBody @Valid RegisterRequest registerRequest
    ){
        var userUpdated = userService.updateUser(email, registerRequest);
        userUpdated.add(linkTo(methodOn(UserController.class).updateUser(email, registerRequest)).withSelfRel());
        BeanUtils.copyProperties(registerRequest, userUpdated);

        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable(value = "id") UUID id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
