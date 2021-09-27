package com.example.spring_boot_jwt_bao.jwt.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RegisterRequest {
    private String username, password;
    private Set<String> roles;
}
