package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.UserEntity;

public interface IUserService {
	List<UserEntity> getUsersList();
    Optional<UserEntity> getUserById(Long id);
    UserEntity saveUser(UserEntity product);
    UserEntity updateUser(UserEntity product);
    void deleteUser(Long id);
}
