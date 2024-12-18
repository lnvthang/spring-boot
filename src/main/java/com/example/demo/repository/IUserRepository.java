package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IGenericRepository<User, Long> {
    User findUserByMail(String mail);
}
