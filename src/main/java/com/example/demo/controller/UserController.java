package com.example.demo.controller;

import com.example.demo.dto.BaseResponse;
import com.example.demo.dto.user.request.CreateUserDTO;
import com.example.demo.dto.user.request.UpdateUserDTO;
import com.example.demo.dto.user.response.UserListResponse;
import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.Validations;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private Validations validations;


	@GetMapping
	public BaseResponse<List<UserListResponse>> getUsersList() {
		BaseResponse<List<UserListResponse>> response = new BaseResponse<>();
		try {
			List<UserListResponse> data = userService.findAll();
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

	@GetMapping("/me")
	public BaseResponse<Object> authenticatedUser() {
		BaseResponse<Object> response = new BaseResponse<>();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			var data = authentication.getPrincipal();
			response.setData(data);
			response.setMessage("Successful");
			response.setStatus(200L);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(500L);
		}
		return response;
	}

	@GetMapping("/{id}")
	public BaseResponse<User> getUserById(@PathVariable String id) {
		BaseResponse<User> response = new BaseResponse<>();
		try {
			User data = userService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()).getBody();;
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
	public BaseResponse<User> createUser(@RequestBody CreateUserDTO userDTO) {
		BaseResponse<User> response = new BaseResponse<>();
		try {
			if (validations.isEmailValid(userDTO.getGmail())) {
				throw new ValidationException("Email is not valid");
			}
			User data = userService.createUser(userDTO);
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
	public BaseResponse<User> updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO userDTO) {
		BaseResponse<User> response = new BaseResponse<>();
		try {
			if (validations.isEmailValid(userDTO.getGmail())) {
				throw new ValidationException("Email is not valid");
			}
			User data = userService.updateUser(id, userDTO);
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
