package com.kaushik.mergeassignment.controllers;

import com.kaushik.mergeassignment.entities.UserEntity;
import com.kaushik.mergeassignment.models.GenericModel;
import com.kaushik.mergeassignment.models.CreateUserRequest;
import com.kaushik.mergeassignment.models.BasicResponse;
import com.kaushik.mergeassignment.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping
    public ResponseEntity<GenericModel<BasicResponse>> createUser(
            @RequestBody @Valid GenericModel<@Valid CreateUserRequest> genericRequest){
        CreateUserRequest userRequest = genericRequest.getData();
        UserEntity user = userService.createUser(userRequest.getUsername(), bCryptPasswordEncoder.encode(userRequest.getPassword()),
                userRequest.getFirstName(), userRequest.getLastName(), userRequest.getRole());
        return new ResponseEntity<>(new GenericModel<>(new BasicResponse(user.getId(), "User created successfully.")), null, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/promotion")
    public ResponseEntity<GenericModel<BasicResponse>> promoteUser(@PathVariable("id") Long id){

        UserEntity user = userService.promoteUser(id);
        return ResponseEntity.ok(new GenericModel<>(new BasicResponse(user.getId(), "User promoted successfully.")));
    }

    @PatchMapping("/{id}/suspension")
    public ResponseEntity<GenericModel<BasicResponse>> suspendUser(@PathVariable("id") Long id){
        UserEntity user = userService.suspendUser(id);
        return ResponseEntity.ok(new GenericModel<>(new BasicResponse(user.getId(), "User suspended successfully.")));
    }
}
