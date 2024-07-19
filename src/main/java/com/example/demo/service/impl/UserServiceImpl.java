package com.example.demo.service.impl;

import com.example.demo.dto.user.CreateUserDTO;
import com.example.demo.dto.user.UpdateUserDTO;
import com.example.demo.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.util.Constants.IS_DELETED;
import static com.example.demo.util.Constants.WORKING;

@Service
public class UserServiceImpl {
    @Autowired
    private GenericServiceImpl<UserModel, Long> genericService;

    public List<UserModel> findAll() {
        List<UserModel> data =  genericService.findAll();
        if (data.isEmpty()) {
            return null;
        }
        return data;
    }

    public Optional<UserModel> findById(Long id) {
        return genericService.findById(id);
    }

    public UserModel createUser(CreateUserDTO userDTO) {
        UserModel createUser = new UserModel();
        createUser.setUsername(userDTO.getUsername());
        createUser.setPassword(userDTO.getPassword());
        createUser.setGmail(userDTO.getGmail());
        createUser.setFull_name(userDTO.getFull_name());
        createUser.setDob(userDTO.getDob());
        createUser.setIs_deleted(IS_DELETED);
        createUser.setStatus(WORKING);
        return genericService.save(createUser);
    }

    public UserModel updateUser(Long id, UpdateUserDTO userDTO) {
        Optional<UserModel> user = findById(id);
        if (user.isPresent()) {
            UserModel updatedUser = user.get();
            updatedUser.setGmail(userDTO.getGmail());
            updatedUser.setFull_name(userDTO.getFull_name());
            updatedUser.setDob(userDTO.getDob());
            updatedUser.setStatus(userDTO.getStatus());
            updatedUser.setIs_deleted(userDTO.getIs_delete());
            return genericService.save(updatedUser);
        } else {
            return null;
        }
    }

    public Boolean deleteUser(Long id) {
        Optional<UserModel> user = findById(id);
        if (user.isPresent()) {
            genericService.delete(id);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    // Define User-specific methods here if needed
}
