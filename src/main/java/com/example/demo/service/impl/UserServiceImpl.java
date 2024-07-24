package com.example.demo.service.impl;

import com.example.demo.dto.user.CreateUserDTO;
import com.example.demo.dto.user.UpdateUserDTO;
import com.example.demo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.Constants.IS_DELETED;
import static com.example.demo.util.Constants.WORKING;

@Service
public class UserServiceImpl {

    @Autowired
    private GenericServiceImpl<UserEntity, Long> genericService;

    public List<UserEntity> findAll() {
        List<UserEntity> data =  genericService.findAll();
        if (data.isEmpty()) {
            return null;
        }
        return data;
    }

    public Optional<UserEntity> findById(Long id) {
        return genericService.findById(id);
    }

    public UserEntity createUser(CreateUserDTO userDTO) {
        UserEntity createUser = new UserEntity();
        createUser.setUsername(userDTO.getUsername());
        createUser.setPassword(userDTO.getPassword());
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
