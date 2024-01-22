package com.example.logindemo.controllers;

import com.example.logindemo.DTO.CreateRoleRequestDTO;
import com.example.logindemo.models.Role;
import com.example.logindemo.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/roles")
@RestController
public class RoleController {
    private RoleService roleService;
    public RoleController (RoleService roleService){
        this.roleService=roleService;
    }

    @PostMapping
        public ResponseEntity<Role> createRole(CreateRoleRequestDTO request){
        Role role= roleService.createRole(request.getName());
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
