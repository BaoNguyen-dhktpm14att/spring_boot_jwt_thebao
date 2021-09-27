package com.example.spring_boot_jwt_bao.jwt.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username, password;
}
