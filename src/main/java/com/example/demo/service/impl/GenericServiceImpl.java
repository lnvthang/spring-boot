package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.demo.repository.user.IUserRepository;
import com.example.demo.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserModel;
import com.example.demo.repository.IGenericRepository;

public class GenericServiceImpl<T, ID> implements IGenericService<T, ID> {
	
	@Autowired
    private IGenericRepository<T, ID> repository;

	@Override
	public List<T> findAll() {
		return (List<T>) repository.findAll();
	}

	@Override
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	@Override
	public T save(T item) {
		return repository.save(item);
	}

	@Override
	public void delete(ID id) {
		repository.deleteById(id);
	}
}
