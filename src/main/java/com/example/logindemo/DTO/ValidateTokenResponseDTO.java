package com.example.logindemo.DTO;

import com.example.logindemo.models.SessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenResponseDTO {
    private UserDTO userDto;
    private SessionStatus sessionStatus;
}
