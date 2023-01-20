package com.chilly.basicspringrestapi.services.interfaces;

import com.chilly.basicspringrestapi.entities.User;
import com.chilly.basicspringrestapi.models.NewUserDto;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(NewUserDto newUserDto);
}
