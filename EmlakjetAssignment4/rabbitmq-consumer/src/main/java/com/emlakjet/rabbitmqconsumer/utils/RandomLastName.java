package com.emlakjet.rabbitmqconsumer.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomLastName {
    public static String generateFirstName() {
        ArrayList<String> lastName = new ArrayList<>(Arrays.asList(
                "ustun",
                "ekin",
                "aktas",
                "kaya",
                "recber",
                "dogan",
                "kartal",
                "bilir",
                "yilmaz",
                "demir",
                "ferman",
                "sahin",
                "celik"
        ));
        Random random = new Random();
        int randomLastName = random.nextInt(50);
        return lastName.get(randomLastName);
    }
}