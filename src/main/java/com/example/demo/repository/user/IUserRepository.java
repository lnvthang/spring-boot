package com.example.demo.repository.user;

import com.example.demo.model.UserModel;
import com.example.demo.repository.IGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends IGenericRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
}
