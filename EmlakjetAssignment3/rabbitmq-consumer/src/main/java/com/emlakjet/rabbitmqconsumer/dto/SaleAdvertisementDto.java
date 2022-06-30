package com.emlakjet.rabbitmqconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok anotations.
@Data
@AllArgsConstructor
@NoArgsConstructor

// we are creating a dto class because we dont want to open our entity class to user.
public class SaleAdvertisementDto {
    private String userName;
    private String userMail;
    private String title;
    private String photo;
    private String detailMessage;
}
