package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.BaseResponse;
import com.example.demo.dto.user.*;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.UserModel;

import static com.example.demo.util.Constants.IS_DELETED;
import static com.example.demo.util.Constants.WORKING;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;


	@GetMapping
	public BaseResponse<List<UserModel>> getUsersList() {
		try {
			BaseResponse<List<UserModel>> response = new BaseResponse<>();
			List<UserModel> data = userService.findAll();
			if (!data.isEmpty()) {
				response.setData(data);
				response.setMessage("Successful");
				response.setStatus(200L);
			}
			else {
				response.setMessage("Failed");
				response.setStatus(500L);
			}
			return response;
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@GetMapping("/{id}")
	public BaseResponse<UserModel> getUserById(@PathVariable Long id) {
		try {
			BaseResponse<UserModel> response = new BaseResponse<>();
			UserModel data = userService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()).getBody();
            response.setData(data);
            response.setMessage("Successful");
            response.setStatus(200L);
			return response;
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PostMapping
	public UserModel createUser(@RequestBody CreateUserDTO userDTO) {
		UserModel createUser = new UserModel();
		createUser.setUsername(userDTO.getUsername());
		createUser.setPassword(userDTO.getPassword());
		createUser.setGmail(userDTO.getGmail());
		createUser.setFullname(userDTO.getFullname());
		createUser.setDob(userDTO.getDob());
		createUser.setIs_deleted(IS_DELETED);
		createUser.setStatus(WORKING);
		return userService.save(createUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO userDTO) {
		Optional<UserModel> user = userService.findById(id);
		if (user.isPresent()) {
			UserModel updatedUser = user.get();
			updatedUser.setGmail(userDTO.getGmail());
			updatedUser.setFullname(userDTO.getFullname());
			updatedUser.setDob(userDTO.getDob());
			updatedUser.setStatus(userDTO.getStatus());
			updatedUser.setIs_deleted(userDTO.getIs_delete());
			return ResponseEntity.ok(userService.save(updatedUser));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		Optional<UserModel> user = userService.findById(id);
		if (user.isPresent()) {
			userService.delete(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
