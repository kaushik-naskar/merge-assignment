package com.kaushik.mergeassignment.controllers;

import com.kaushik.mergeassignment.models.AuthRequest;
import com.kaushik.mergeassignment.models.AuthResponse;
import com.kaushik.mergeassignment.models.GenericModel;
import com.kaushik.mergeassignment.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> createToken(@RequestBody AuthRequest request){
        String token = authService.createToken(request.getUsername(), request.getPassword());
        AuthResponse response = new AuthResponse(token);
        return ResponseEntity.ok(response);
    }
}
