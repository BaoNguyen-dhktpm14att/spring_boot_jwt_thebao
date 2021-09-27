package com.example.spring_boot_jwt_bao.controller;

import com.spring_boot_jwt_bao.entity.Token;
import com.spring_boot_jwt_bao.entity.User;
import com.spring_boot_jwt_bao.jwt.JwtUtils;
import com.spring_boot_jwt_bao.jwt.UserDetailsImpl;
import com.spring_boot_jwt_bao.jwt.payload.LoginRequest;
import com.spring_boot_jwt_bao.jwt.payload.RegisterRequest;
import com.spring_boot_jwt_bao.service.TokenService;
import com.spring_boot_jwt_bao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        if (userService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new String("Error: Username(" + registerRequest.getUsername() + ") is already taken!"));
        }
        registerRequest.setPassword(encoder.encode(registerRequest.getPassword()));
        return ResponseEntity.ok(userService.createUser(new User(registerRequest.getUsername(),registerRequest.getPassword())));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {

        UserDetailsImpl userDetails = userService.findByUsername(loginRequest.getUsername());
        if (userDetails == null){
            return ResponseEntity.badRequest().body("Account is not valid");
        }
        if (!encoder.matches(loginRequest.getPassword(),userDetails.getPassword())){
            return ResponseEntity.badRequest().body("Password is not valid");
        }
        Token token = new Token();
        token.setToken(jwtUtils.generateJwtToken(userDetails));
        token.setTokenExpDate(jwtUtils.generateExpirationDate());
        token.setCreatedBy(userDetails.getUserId()+"");
        tokenService.createToken(token);
        return ResponseEntity.ok(token);



    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity hello(){
        return ResponseEntity.ok("hello");
    }
}