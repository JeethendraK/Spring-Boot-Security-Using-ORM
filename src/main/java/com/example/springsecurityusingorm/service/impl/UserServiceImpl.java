package com.example.springsecurityusingorm.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springsecurityusingorm.entity.User;
import com.example.springsecurityusingorm.repo.UserRepository;
import com.example.springsecurityusingorm.service.IUserService;

public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repo;

    @Override
    public Integer saveUser(User user) {
        return repo.save(user).getUserId();
    }

    @Override
    public Optional<User> getOneUser(Integer id) {
        return repo.findById(id);
    }

}
