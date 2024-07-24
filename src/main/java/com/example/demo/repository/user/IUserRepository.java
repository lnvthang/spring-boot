package com.example.demo.repository.user;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends IGenericRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
