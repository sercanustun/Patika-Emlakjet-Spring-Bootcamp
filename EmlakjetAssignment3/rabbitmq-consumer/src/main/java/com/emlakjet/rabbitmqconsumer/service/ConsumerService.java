package com.emlakjet.rabbitmqconsumer.service;

import com.emlakjet.rabbitmqconsumer.model.SaleAdvertisement;
import com.emlakjet.rabbitmqconsumer.repository.SaleAdvertisementRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

// we defined this is component
@Component
@AllArgsConstructor

public class ConsumerService {

    // again we dont need autowired annotation because spring does it for us from allargscons annotation.
    private final SaleAdvertisementRepository saleAdvertisementRepository;

// we are telling spring listen this que and if there is a request from this que do that.
    @RabbitListener(queues = "${queue.name}")
    public void listen(@Payload SaleAdvertisement saleAdvertisement) throws InterruptedException {
        Thread.sleep(3*10000);
        saleAdvertisement.setPriceGraphPath("Updated by Worker 1");
        saleAdvertisementRepository.save(saleAdvertisement);
    }
}