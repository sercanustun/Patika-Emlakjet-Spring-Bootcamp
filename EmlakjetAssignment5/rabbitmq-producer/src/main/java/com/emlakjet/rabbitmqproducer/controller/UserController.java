package com.emlakjet.rabbitmqproducer.controller;


import com.emlakjet.rabbitmqproducer.dto.UserDto;
import com.emlakjet.rabbitmqproducer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    // we dont need autowired anotation because spring does that for us with AllArgsConstructor

    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }
}