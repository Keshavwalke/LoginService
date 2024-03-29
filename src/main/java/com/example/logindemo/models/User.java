package com.example.logindemo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends  BaseModel{
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set <Role> roles =new HashSet<>();
}
