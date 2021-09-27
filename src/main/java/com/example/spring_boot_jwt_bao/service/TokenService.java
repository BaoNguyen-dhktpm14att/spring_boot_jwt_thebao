package com.example.spring_boot_jwt_bao.service;

import com.spring_boot_jwt_bao.entity.Token;
import org.springframework.stereotype.Service;

public interface TokenService {
    Token createToken(Token token);

    Token findByToken(String token);
}
