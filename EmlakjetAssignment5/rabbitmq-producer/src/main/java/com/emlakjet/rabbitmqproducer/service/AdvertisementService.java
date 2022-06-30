package com.emlakjet.rabbitmqproducer.service;

import com.emlakjet.rabbitmqproducer.dto.AdvertisementDto;
import com.emlakjet.rabbitmqproducer.model.Advertisement;
import com.emlakjet.rabbitmqproducer.repository.AdvertisementRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;


    public void createAdvertisement(AdvertisementDto advertisementDto) {

        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(this.queue.getName(), advertisementDto);

        }
    }

    public List<Advertisement> findAdvertisementByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return advertisementRepository.findAdvertisementByCreatedAtBetween(startDate, endDate);
    }


    public List<Advertisement> findAdvertisementByTitleContainingIgnoreCase(String title) {
        return advertisementRepository.findAdvertisementByTitleContainingIgnoreCase(title);
    }


    public List<Advertisement> findAdvertisementByDescriptionContainingIgnoreCase(String description) {
        return advertisementRepository.findAdvertisementByDescriptionContainingIgnoreCase(description);
    }

    public List<Advertisement> findAdvertisementByPriceBetween(long priceStart, long priceEnd) {
        return advertisementRepository.findAdvertisementByPriceBetween(priceStart, priceEnd);
    }

    public List<Advertisement> findTop10AdvertisementOrOrderByCreatedAt(LocalDateTime createdAt) {
        return advertisementRepository.findTop10AdvertisementOrOrderByCreatedAt(createdAt);
    }

}