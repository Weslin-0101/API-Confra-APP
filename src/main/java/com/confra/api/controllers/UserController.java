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
@RequestMapping(
        value = "root/confra/api/v1"
)
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/create")
    public ResponseEntity<RegisterResponse> saveProduct(@RequestBody @Valid RegisterRequest registerRequest) {
        var user = new User();
        BeanUtils.copyProperties(registerRequest, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createAccount(registerRequest));
    }
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.findAll();
        if (!userList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable(value="id") UUID id){
        var user = userService.findById(id);
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
//      user.add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("User List"));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<User> updateUser (@PathVariable(value = "id") UUID id,
                                            @RequestBody @Valid RegisterRequest registerRequest){
        Optional<User> userObject = Optional.ofNullable(userService.findById(id));
        if (userObject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var user = userObject.get();
        BeanUtils.copyProperties(registerRequest, user);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser (@PathVariable(value = "id") UUID id){
        Optional<User> userObject = Optional.ofNullable(userService.findById(id));
        if (userObject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userService.delete(userObject.get());
        return ResponseEntity.status(HttpStatus.OK).body(userObject.get());
    }
}
