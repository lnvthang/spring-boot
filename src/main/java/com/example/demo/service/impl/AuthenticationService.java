package com.example.demo.service.impl;

import com.example.demo.dto.authen.LoginRequest;
import com.example.demo.dto.user.request.CreateUserDTO;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.demo.util.Constants.IS_DELETED;
import static com.example.demo.util.Constants.WORKING;

@Service
public class AuthenticationService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserEntity signup(CreateUserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setFull_name(userDTO.getFull_name());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setGmail(userDTO.getGmail());
        user.setFull_name(userDTO.getFull_name());
        user.setDob(userDTO.getDob());
        user.setIs_deleted(IS_DELETED);
        user.setStatus(WORKING);
        return userRepository.save(user);
    }

    public UserEntity authenticate(LoginRequest input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.getUsername(),
                            input.getPassword()
                    )
            );
            return userRepository.findByUsername(input.getUsername()).orElseThrow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
