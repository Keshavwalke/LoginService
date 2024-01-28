package com.example.logindemo.controllers;

import com.example.logindemo.DTO.ErrorResponseDTO;
import com.example.logindemo.exceptions.UserDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {


    @ExceptionHandler(UserDoesNotExistsException.class)
    public ResponseEntity<ErrorResponseDTO> HandleUserNotExist(Exception e){
        ErrorResponseDTO errorResposeDTO=new ErrorResponseDTO();
        errorResposeDTO.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorResposeDTO, HttpStatus.NOT_FOUND);
    }
}
