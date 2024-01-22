package com.example.logindemo.services;

import com.example.logindemo.DTO.UserDTO;
import com.example.logindemo.exceptions.UserAlreadyExistsException;
import com.example.logindemo.repositories.SessionRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.logindemo.models.User;
import com.example.logindemo.repositories.UserRepository;
import java.util.Optional;

@Service
public class AuthService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.sessionRepository=sessionRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public UserDTO signUp (String email, String password) throws UserAlreadyExistsException {
        //code to check if the user is already present in the database
        Optional<User> userOptional= userRepository.findByEmail(email);
        if(!userOptional.isEmpty()){
            throw new UserAlreadyExistsException("User with email already exist...");
        }
        //this means user not present in database so storing in database
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        User savedUser =userRepository.save(user);
        return UserDTO.from(savedUser);
    }



    public void login(){

    }

    public void validate(){

    }
}
