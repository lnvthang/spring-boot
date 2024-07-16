package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
    private IUserRepository userRepository;

	@Override
	public List<UserEntity> getUsersList() {
		return userRepository.findAll();
	}

	@Override
	public Optional<UserEntity> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public UserEntity saveUser(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
