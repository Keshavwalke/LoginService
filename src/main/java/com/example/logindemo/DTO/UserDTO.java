package com.example.logindemo.DTO;

import com.example.logindemo.models.Role;
import com.example.logindemo.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private String email;
    private Set<Role> roles= new HashSet<>();

    public static UserDTO from(User user){
        UserDTO userDTO= new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }
}
