package com.example.demo.controller;

import com.example.demo.dto.authen.LoginRequest;
import com.example.demo.dto.authen.LoginResponse;
import com.example.demo.dto.user.request.CreateUserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.AuthenticationService;
import com.example.demo.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Boolean> register(@RequestBody CreateUserDTO registerUserDto) {
        UserEntity registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        UserEntity authenticatedUser = authenticationService.authenticate(loginRequest);

        String jwtToken = jwtService.generateToken((UserDetails) authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello from Eureka Client!";
    }
}
