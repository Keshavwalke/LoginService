package com.example.logindemo.security.models;

import com.example.logindemo.models.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


//technically these classes are adapters

@JsonDeserialize
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
    //    private Role role;
    private String authority;

    public CustomGrantedAuthority(Role role) {
        this.authority = role.getRole();
    }


    @Override
    public String getAuthority() {
        return this.authority;
    }
}