package com.example.logindemo.controllers;

import com.example.logindemo.DTO.*;
import com.example.logindemo.exceptions.UserAlreadyExistsException;
import com.example.logindemo.exceptions.UserDoesNotExistsException;
import com.example.logindemo.models.SessionStatus;
import com.example.logindemo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;
    }


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login (@RequestBody LoginRequestDTO request) throws UserAlreadyExistsException, UserDoesNotExistsException {
        return authService.login(request.getEmail(), request.getPassword());
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDTO request){
        return authService.logout(request.getToken(), request.getUserId());
        //void return type in generics is uppercase(Void)
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp (@RequestBody SignUpRequestDTO request) throws UserAlreadyExistsException {
        UserDTO userDTO= authService.signUp(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateTokenResponseDTO> validateToken(@RequestBody ValidateTokenRequestDTO request){
        Optional<UserDTO>  userDTO= authService.validate(request.getToken(), request.getUserId());
        if(userDTO.isEmpty()){
            ValidateTokenResponseDTO response=new ValidateTokenResponseDTO();
            response.setSessionStatus(SessionStatus.INVALID);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        ValidateTokenResponseDTO response=new ValidateTokenResponseDTO();
        response.setSessionStatus(SessionStatus.ACTIVE);
        response.setUserDto(userDTO.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

