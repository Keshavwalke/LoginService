package com.example.logindemo.controllers;

import com.example.logindemo.DTO.ErrorResponseDTO;
import com.example.logindemo.exceptions.UserAlreadyExistsException;
import com.example.logindemo.exceptions.UserDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {


    //This tells hey exception handler whenever you find the this exception please throw this method
    @ExceptionHandler(UserDoesNotExistsException.class)
    public ResponseEntity<ErrorResponseDTO> HandleUserNotExist(Exception e){
        ErrorResponseDTO errorResposeDTO=new ErrorResponseDTO();
        errorResposeDTO.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorResposeDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
        public ResponseEntity<ErrorResponseDTO>  HandleUserAlreadyExist(Exception e){
            ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
            errorResponseDTO.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
        }

}
