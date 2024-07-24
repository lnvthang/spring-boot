package com.example.demo.service.impl;

import com.example.demo.dto.user.request.CreateUserDTO;
import com.example.demo.dto.user.request.UpdateUserDTO;
import com.example.demo.dto.user.response.UserListResponse;
import com.example.demo.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demo.util.Constants.IS_DELETED;
import static com.example.demo.util.Constants.WORKING;

@Service
public class UserServiceImpl {

    @Autowired
    private GenericServiceImpl<UserEntity, Long> genericService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserListResponse> findAll() {
        List<UserEntity> data =  genericService.findAll();
        if (data.isEmpty()) {
            return null;
        }
        return data.stream()
                .filter(item -> item.getIs_deleted() == 0L)
                .map(user -> modelMapper.map(user, UserListResponse.class))
                .collect(Collectors.toList());
    }

    public Optional<UserEntity> findById(Long id) {
        return genericService.findById(id);
    }

    public UserEntity createUser(CreateUserDTO userDTO) {
        UserEntity createUser = new UserEntity();
        createUser.setUsername(userDTO.getUsername());
        createUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        createUser.setGmail(userDTO.getGmail());
        createUser.setFull_name(userDTO.getFull_name());
        createUser.setDob(userDTO.getDob());
        createUser.setIs_deleted(IS_DELETED);
        createUser.setStatus(WORKING);
        return genericService.save(createUser);
    }

    public UserEntity updateUser(Long id, UpdateUserDTO userDTO) {
        Optional<UserEntity> user = findById(id);
        if (user.isPresent()) {
            UserEntity updatedUser = user.get();
            updatedUser.setGmail(userDTO.getGmail());
            updatedUser.setFull_name(userDTO.getFull_name());
            updatedUser.setDob(userDTO.getDob());
            updatedUser.setStatus(userDTO.getStatus());
            updatedUser.setIs_deleted(userDTO.getIs_deleted());
            updatedUser.setUpdated_at(new Date());
            return genericService.save(updatedUser);
        } else {
            return null;
        }
    }

    public Boolean deleteUser(Long id) {
        Optional<UserEntity> user = findById(id);
        if (user.isPresent()) {
            genericService.delete(id);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
