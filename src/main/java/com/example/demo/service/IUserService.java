package com.example.demo.service;

import com.example.demo.model.UserModel;

import java.util.Optional;

public interface IUserService {
    Optional<UserModel> findByUserName(String username);
}
