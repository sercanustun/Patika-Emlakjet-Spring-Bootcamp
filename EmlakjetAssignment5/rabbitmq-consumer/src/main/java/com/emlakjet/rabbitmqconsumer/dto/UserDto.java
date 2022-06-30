package com.emlakjet.rabbitmqconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// we are creating a dto class because we dont want to open our entity class to user.

// lombok annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
}