package com.example.demo.service;

import com.example.demo.entity.UserEntity;

import java.util.Optional;

public interface IUserService {
    Optional<UserEntity> findByUserName(String username);
}
