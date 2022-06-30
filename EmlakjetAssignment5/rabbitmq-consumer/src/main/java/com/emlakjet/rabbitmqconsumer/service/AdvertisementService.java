package com.emlakjet.rabbitmqconsumer.service;

import com.emlakjet.rabbitmqconsumer.dto.AdvertisementDto;
import com.emlakjet.rabbitmqconsumer.model.Advertisement;
import com.emlakjet.rabbitmqconsumer.model.User;
import com.emlakjet.rabbitmqconsumer.repository.AdvertisementRepository;
import com.emlakjet.rabbitmqconsumer.repository.UserRepository;
import com.emlakjet.rabbitmqconsumer.utils.RandomDescription;
import com.emlakjet.rabbitmqconsumer.utils.RandomTitle;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

// Our service class

@AllArgsConstructor
@Service
public class AdvertisementService {
    // we didnt define auto wired because spring does it for us with allargs annotation.
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;


    @RabbitListener(queues = "${queue.name}")
    public void createAdvertisement(AdvertisementDto advertisementDto) throws InterruptedException {
        Thread.sleep(60 * 1000);
        advertisementDto.setDescription(RandomDescription.generateDescription());
        advertisementDto.setTitle(RandomTitle.generateTitle());
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(advertisementDto.getTitle());
        advertisement.setDescription(advertisementDto.getDescription());
        Random random = new Random();
        Long randomUserId = (long) random.nextInt(userRepository.getNumberOfUsers());
        advertisement.setUser(new User(randomUserId));
        advertisement.setCreatedAt(LocalDateTime.now());
        advertisementRepository.save(advertisement);
    }


}