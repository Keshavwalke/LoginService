package com.example.logindemo.services;

import com.example.logindemo.DTO.UserDTO;
import com.example.logindemo.exceptions.UserAlreadyExistsException;
import com.example.logindemo.models.Session;
import com.example.logindemo.models.SessionStatus;
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

import java.util.Date;
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
        String token= RandomStringUtils.randomAscii(20);
        MultiValueMap<String,String> hdr= new LinkedMultiValueMap<>();
        hdr.add("AUTH_TOKEN", token);

        Session session =new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);
        sessionRepository.save(session);

        UserDTO userDTO= UserDTO.from(user);
        ResponseEntity<UserDTO> response= new ResponseEntity<>(
                     userDTO, hdr, HttpStatus.OK);
        return response;
    }

    public SessionStatus validate(String token, Long userId){
        //tried to check token exist in DB , if not exist return INVALID
        Optional<Session> sessionOptional=sessionRepository.findByTokenAndUser_Id(token, userId);
        if(sessionOptional.isEmpty()){
            return SessionStatus.INVALID;
        }
        //if  session is not active then returned EXPIRED
        Session session=sessionOptional.get();
        if(!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
            return SessionStatus.EXPIRED;
        }
//        if (!session.getExpiringAt().after(new Date())) {
//            return SessionStatus.EXPIRED;           //check if session is expired
//        }

        return SessionStatus.ACTIVE;           //that means this is active session
    }



    public ResponseEntity<Void> logout(String token, Long userId){
        Optional<Session> sessionOptional=sessionRepository.findByTokenAndUser_Id(token, userId);
        if(sessionOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Session session=sessionOptional.get();
        session.setSessionStatus(SessionStatus.LOGGED_OUT);
        session.setDeleted(true);
        sessionRepository.save(session);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
