package com.emlakjet.rabbitmqproducer.service;

import com.emlakjet.rabbitmqproducer.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final RabbitTemplate rabbitTemplate;

    private final Queue queue;


    public void createUser(UserDto userDto) {

        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(this.queue.getName(), userDto);

        }
    }
}