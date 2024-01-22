package com.example.logindemo.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Session extends BaseModel{
    @ManyToOne
    private User user;
    private String token;
    private Date ExpiringAt;

    @Enumerated(EnumType.ORDINAL)           //ORDINAL MEANS IT'LL STORE 1,2,3 AS VALUE OF ENUM
    private SessionStatus sessionStatus;
}
