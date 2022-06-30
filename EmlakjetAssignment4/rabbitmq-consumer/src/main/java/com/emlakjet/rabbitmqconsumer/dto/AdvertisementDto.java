package com.emlakjet.rabbitmqconsumer.dto;

import lombok.Data;
// we are creating a dto class because we dont want to open our entity class to user.

// lombok anotations.
@Data
public class AdvertisementDto {
    private String title;
    private String description;
    private long price;

}