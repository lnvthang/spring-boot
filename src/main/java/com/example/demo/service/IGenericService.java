package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface IGenericService<T, ID>{
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T entity);
    void delete(ID id);
}