package com.crio.stayease.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    private String email;
    
    private String password;

    private String firstName;

    private String lastName;

   
}