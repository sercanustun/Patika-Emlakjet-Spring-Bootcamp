package com.emlakjet.rabbitmqconsumer.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


// our util classes we use random class to pick descriptions or others from arraylist ( bu classda pelinin ödevinden yararlandım https://github.com/196-Emlakjet-Java-Spring-Bootcamp/week-4-assignment4-pelinhangisi)
public class RandomDescription {
    public static String generateDescription() {
        ArrayList<String> description = new ArrayList<>(Arrays.asList(
                "İhtiyaçtan satılık",
                "Asansörlü",
                "Kombili",
                "Fiber altyapisi var",
                "Guven Emlak",
                "Aktas Emlak"
        ));
        Random random = new Random();
        int randomDescription = random.nextInt(description.size());
        return description.get(randomDescription);

    }
}
