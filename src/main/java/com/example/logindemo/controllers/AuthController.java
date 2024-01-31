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

        // was trying global exception not worked
//        ResponseEntity<UserDTO> res= authService.login(request.getEmail(), request.getPassword());
//        if(res.hasBody()){
//            throw new UserDoesNotExistsException("user does not exists");
//        }
//        return res;
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
    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDTO request){
        SessionStatus sessionStatus= authService.validate(request.getToken(), request.getUserId());
        return new ResponseEntity<>(sessionStatus, HttpStatus.OK);
    }
}

