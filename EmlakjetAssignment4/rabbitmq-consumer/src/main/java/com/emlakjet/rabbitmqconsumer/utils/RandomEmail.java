package com.emlakjet.rabbitmqconsumer.utils;

public class RandomEmail {
    public static String generateEmail(String firstName, String lastName) {
        return (firstName + lastName + "@mail.com");
    }
}