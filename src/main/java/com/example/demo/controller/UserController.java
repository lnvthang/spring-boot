package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.BaseResponse;
import com.example.demo.dto.user.*;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.Validations;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.UserModel;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private Validations validations;


	@GetMapping
	public BaseResponse<List<UserModel>> getUsersList() {
		BaseResponse<List<UserModel>> response = new BaseResponse<>();
		try {
			List<UserModel> data = userService.findAll()
												.stream()
												.filter(item -> item.getIs_deleted() == 0L)
												.collect(Collectors.toList());
			if (!data.isEmpty()) {
				response.setData(data);
			}
			response.setMessage("Successful");
			response.setStatus(200L);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(500L);
        }
		return response;
	}

	@GetMapping("/{id}")
	public BaseResponse<UserModel> getUserById(@PathVariable Long id) {
		BaseResponse<UserModel> response = new BaseResponse<>();
		try {
			UserModel data = userService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()).getBody();;
            response.setData(data);
            response.setMessage("Successful");
            response.setStatus(200L);
        } catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(500L);
		}
		return response;
	}

	@PostMapping
	public BaseResponse<UserModel> createUser(@RequestBody CreateUserDTO userDTO) {
		BaseResponse<UserModel> response = new BaseResponse<>();
		try {
			if (validations.isEmailValid(userDTO.getGmail())) {
				throw new ValidationException("Email is not valid");
			}
			UserModel data = userService.createUser(userDTO);
			response.setData(data);
			response.setMessage("Successful");
			response.setStatus(200L);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(500L);
		}
		return response;
	}

	@PutMapping("/{id}")
	public BaseResponse<UserModel> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO userDTO) {
		BaseResponse<UserModel> response = new BaseResponse<>();
		try {
			if (validations.isEmailValid(userDTO.getGmail())) {
				throw new ValidationException("Email is not valid");
			}
			UserModel data = userService.updateUser(id, userDTO);
			response.setData(data);
			response.setMessage("Successful");
			response.setStatus(200L);
			return response;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(500L);
		}
		return response;
	}

	@DeleteMapping("/{id}")
	public BaseResponse<Boolean> deleteUser(@PathVariable Long id) {
		BaseResponse<Boolean> response = new BaseResponse<>();
		try {
			Boolean data = userService.deleteUser(id);
			response.setData(data);
			response.setMessage("Successful");
			response.setStatus(200L);
			return response;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(500L);
		}
		return response;
	}
}
