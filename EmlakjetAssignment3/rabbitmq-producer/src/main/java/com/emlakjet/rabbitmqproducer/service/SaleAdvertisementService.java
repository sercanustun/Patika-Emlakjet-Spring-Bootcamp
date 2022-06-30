package com.emlakjet.rabbitmqproducer.service;

import com.emlakjet.rabbitmqproducer.dto.SaleAdvertisementDto;
import com.emlakjet.rabbitmqproducer.model.SaleAdvertisement;
import com.emlakjet.rabbitmqproducer.model.User;
import com.emlakjet.rabbitmqproducer.repository.SaleAdvertisementRepository;
import com.emlakjet.rabbitmqproducer.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

// Our service class
@AllArgsConstructor
@Service
public class SaleAdvertisementService {


    // we didnt define auto wired because spring does it for us with allargs annotation.
    private final SaleAdvertisementRepository saleAdvertisementRepository;
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;
    private final  Queue queue;




    public void createSaleAdvertisement(SaleAdvertisementDto saleAdvertisementDto) {
        User user = User.builder()
                .name(saleAdvertisementDto.getUserName())
                .email(saleAdvertisementDto.getUserMail())
                .build();
         userRepository.save(user);

        SaleAdvertisement saleAdvertisement = SaleAdvertisement.builder()
                .title(saleAdvertisementDto.getTitle())
                .photo(saleAdvertisementDto.getPhoto())
                .detailMessage(saleAdvertisementDto.getDetailMessage())
                .build();
         saleAdvertisementRepository.save(saleAdvertisement);

        rabbitTemplate.convertAndSend(queue.getName(), saleAdvertisement);

    }


}