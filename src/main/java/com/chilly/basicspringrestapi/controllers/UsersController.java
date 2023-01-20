package com.chilly.basicspringrestapi.controllers;

import com.chilly.basicspringrestapi.entities.User;
import com.chilly.basicspringrestapi.models.NewUserDto;
import com.chilly.basicspringrestapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.ok(users).build();
    }

    @PostMapping
    public Response addNewUser(@RequestBody NewUserDto newUserDto) {
        userService.addUser(newUserDto);
        return Response.ok().build();
    }
}
