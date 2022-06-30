package com.emlakjet.rabbitmqproducer.dto;

import lombok.Data;

// lombok anotations.
@Data
public class AdvertisementDto {
    private String title;
    private String description;
    private long price;

}