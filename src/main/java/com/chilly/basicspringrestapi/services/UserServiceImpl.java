package com.chilly.basicspringrestapi.services;

import com.chilly.basicspringrestapi.entities.User;
import com.chilly.basicspringrestapi.models.NewUserDto;
import com.chilly.basicspringrestapi.repositories.UserRepository;
import com.chilly.basicspringrestapi.services.exceptions.UserExistsException;
import com.chilly.basicspringrestapi.services.exceptions.UserNotFoundException;
import com.chilly.basicspringrestapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(NewUserDto newUserDto) {
        if (userRepository.existsByEmail(newUserDto.getEmail()))
            throw new UserExistsException();

        // bCrypt hashing
        BCrypt bCrypt = new BCrypt();
        String salt = bCrypt.gensalt(10);
        String hashedPwd = bCrypt.hashpw(newUserDto.getPassword(), salt);

        // save new user
        User newUser = new User();
        newUser.setEmail(newUserDto.getEmail());
        newUser.setPassword(hashedPwd);
        userRepository.save(newUser);
    }
}
