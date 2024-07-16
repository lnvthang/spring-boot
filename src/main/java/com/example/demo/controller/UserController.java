package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping
	public List<UserEntity> getUsersList() {
		return userService.getUsersList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
		Optional<UserEntity> user = userService.getUserById(id);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public UserEntity createUser(@RequestBody UserEntity user) {
		return userService.saveUser(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userDetails) {
		Optional<UserEntity> user = userService.getUserById(id);
		if (user.isPresent()) {
			UserEntity updatedUser = user.get();
			updatedUser.setUsername(userDetails.getFullname());
			updatedUser.setPassword(userDetails.getPassword());
			updatedUser.setGmail(userDetails.getGmail());
			updatedUser.setFullname(userDetails.getFullname());
			updatedUser.setDob(userDetails.getDob());
			updatedUser.setStatus(userDetails.getStatus());

			return ResponseEntity.ok(userService.updateUser(updatedUser));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		Optional<UserEntity> user = userService.getUserById(id);
		if (user.isPresent()) {
			userService.deleteUser(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
