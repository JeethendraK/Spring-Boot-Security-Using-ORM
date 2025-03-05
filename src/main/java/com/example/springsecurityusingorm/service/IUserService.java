package com.example.springsecurityusingorm.service;

import java.util.Optional;

import com.example.springsecurityusingorm.entity.User;

public interface IUserService {
    Integer saveUser(User user);
    Optional<User> getOneUser(Integer id);
}
