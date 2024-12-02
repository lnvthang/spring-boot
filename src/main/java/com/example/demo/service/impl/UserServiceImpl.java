package com.example.demo.service.impl;

import com.example.demo.dto.user.request.CreateUserDTO;
import com.example.demo.dto.user.request.UpdateUserDTO;
import com.example.demo.dto.user.response.UserListResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.user.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private GenericServiceImpl<User, Long> genericService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    public List<UserListResponse> findAll() {
        List<User> data =  genericService.findAll();
        if (data.isEmpty()) {
            return null;
        }
//        return data.stream()
//                .filter(item -> item.getIs_deleted() == 0L)
//                .map(user -> modelMapper.map(user, UserListResponse.class))
//                .collect(Collectors.toList());
        return null;
    }

    public Optional<User> findById(String id) {
       return Optional.of(userRepository.findByUsername(id).orElseThrow());
//        return genericService.findById(id);
    }

    public User createUser(CreateUserDTO userDTO) {
        User createUser = new User();
//        createUser.setUsername(userDTO.getUsername());
//        createUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        createUser.setGmail(userDTO.getGmail());
//        createUser.setFull_name(userDTO.getFull_name());
//        createUser.setDob(userDTO.getDob());
//        createUser.setIs_deleted(IS_DELETED);
//        createUser.setStatus(WORKING);
        return genericService.save(createUser);
    }

    public User updateUser(Long id, UpdateUserDTO userDTO) {
        Optional<User> user = findById(String.valueOf(id));
        if (user.isPresent()) {
            User updatedUser = user.get();
//            updatedUser.setGmail(userDTO.getGmail());
//            updatedUser.setFull_name(userDTO.getFull_name());
//            updatedUser.setDob(userDTO.getDob());
//            updatedUser.setStatus(userDTO.getStatus());
//            updatedUser.setIs_deleted(userDTO.getIs_deleted());
//            updatedUser.setUpdated_at(new Date());
            return genericService.save(updatedUser);
        } else {
            return null;
        }
    }

    public Boolean deleteUser(Long id) {
        Optional<User> user = findById(String.valueOf(id));
        if (user.isPresent()) {
            genericService.delete(id);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
