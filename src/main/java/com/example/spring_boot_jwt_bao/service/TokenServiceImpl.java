package com.example.spring_boot_jwt_bao.service;

import com.spring_boot_jwt_bao.entity.Token;
import com.spring_boot_jwt_bao.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository repository;

    @Override
    public Token createToken(Token token) {
        return repository.saveAndFlush(token);
    }

    @Override
    public Token findByToken(String token) {
        return repository.findByToken(token).get();
    }
}
