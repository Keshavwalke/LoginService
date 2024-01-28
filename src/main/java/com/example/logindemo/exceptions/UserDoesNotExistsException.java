package com.example.logindemo.exceptions;

public class UserDoesNotExistsException extends Exception{
    public UserDoesNotExistsException (String message){
        super(message);
    }
}
