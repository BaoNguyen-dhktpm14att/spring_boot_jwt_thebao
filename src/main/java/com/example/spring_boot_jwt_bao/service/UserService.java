package com.example.spring_boot_jwt_bao.service;

import com.spring_boot_jwt_bao.jwt.UserDetailsImpl;
import com.spring_boot_jwt_bao.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User createUser(User user);

    Boolean existsByUsername(String username);

    UserDetailsImpl findByUsername(String username) throws UsernameNotFoundException;
}
