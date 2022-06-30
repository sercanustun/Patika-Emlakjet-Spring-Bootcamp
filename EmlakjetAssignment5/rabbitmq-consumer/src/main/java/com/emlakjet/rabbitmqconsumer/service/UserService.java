package com.emlakjet.rabbitmqconsumer.service;


import com.emlakjet.rabbitmqconsumer.dto.UserDto;
import com.emlakjet.rabbitmqconsumer.model.User;
import com.emlakjet.rabbitmqconsumer.repository.UserRepository;
import com.emlakjet.rabbitmqconsumer.utils.RandomEmail;
import com.emlakjet.rabbitmqconsumer.utils.RandomFirstName;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

// Our service class

@AllArgsConstructor
@Service
public class UserService {
    // we didnt define auto wired because spring does it for us with allargs annotation.

    private final UserRepository userRepository;


    @RabbitListener(queues = "${queue.name}")
    public void createUser(@Payload UserDto userDto) {
        userDto.setFirstName(RandomFirstName.generateFirstName());
        userDto.setLastName(RandomFirstName.generateFirstName());
        userDto.setEmail(RandomEmail.generateEmail(userDto.getFirstName(), userDto.getLastName()));
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);

    }
}