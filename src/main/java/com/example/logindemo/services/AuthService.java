package com.example.logindemo.services;

import com.example.logindemo.DTO.UserDTO;
import com.example.logindemo.exceptions.UserAlreadyExistsException;
import com.example.logindemo.repositories.SessionRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.logindemo.models.User;
import com.example.logindemo.repositories.UserRepository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.Optional;

@Service
public class AuthService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
 //   private SessionRepository sessionRepository;
    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
  //      this.sessionRepository=sessionRepository;
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



    public ResponseEntity<UserDTO> login(String email, String password) throws UserAlreadyExistsException {
        //found the user in DB by email and checked if not present thrown excpetion
        Optional<User> userOptional=userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UserAlreadyExistsException("User with email"+email +" does not exists...");
        }
        //Got details of user,checked if User given password in request matched with DB password
        User user= userOptional.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){ //(raw password,Encoded password)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Since user is valid, creating one token for him, create token as well
        String token= RandomStringUtils.random(20);
        MultiValueMap<String,String> hdr= new LinkedMultiValueMap<>();
        hdr.add("AUTH_TOKEN", token);

        UserDTO userDTO= UserDTO.from(user);
        ResponseEntity<UserDTO> response= new ResponseEntity<>(
                     userDTO, hdr, HttpStatus.OK);
        return response;
    }

    public void validate(){

    }
}
