package com.example.logindemo.controllers;

import com.example.logindemo.DTO.*;
import com.example.logindemo.exceptions.UserAlreadyExistsException;
import com.example.logindemo.models.SessionStatus;
import com.example.logindemo.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login (@RequestBody LoginRequestDTO request){
        return authService.login(request.getEmail(), request.getPassword());
    }


    @PostMapping("/logout")
    public ResponseEntity<void> logout(@RequestBody LogoutRequestDTO request){
        return authService.logout(request.getToken(), request.getUserId());
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp (@RequestBody SignUpRequestDTO request) throws UserAlreadyExistsException {
        UserDTO userDTO= authService.signUp(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(ValidateTokenRequestDTO request){
        SessionStatus sessionStatus= authService.validate(request.getToken(), request.getUserId());
        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }
}

