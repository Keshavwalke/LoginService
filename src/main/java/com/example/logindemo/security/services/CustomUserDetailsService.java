package com.example.logindemo.security.services;

import com.example.logindemo.repositories.UserRepository;
import com.example.logindemo.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.logindemo.models.User;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =userRepository.findByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("bro your user with email: "+username +" is not there...");
        }

        return new CustomUserDetails(user.get());
    }
}
