package com.example.springSecurity.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest 
{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

    private AddressRequest address;
    
}
