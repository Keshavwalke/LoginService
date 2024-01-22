package com.example.logindemo.controllers;

import com.example.logindemo.DTO.SetUserRoleRequestDTO;
import com.example.logindemo.DTO.UserDTO;
import com.example.logindemo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable("id") Long userId){
       UserDTO userDTO= userService.getUserDetails(userId);
       return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDTO> setUserRoles(
            @PathVariable("id") Long userId, @RequestBody SetUserRoleRequestDTO request){
            UserDTO userDTO= userService.setUserRoles(userId, request.getRoleIds());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
