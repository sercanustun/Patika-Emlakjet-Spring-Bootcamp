package com.emlakjet.rabbitmqconsumer.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomFirstName {

    public static String generateFirstName() {
        ArrayList<String> name = new ArrayList<>(Arrays.asList(
                "sercan",
                "melih",
                "ali",
                "veli",
                "ahmet",
                "guven",
                "zehra",
                "ayse",
                "ilknur",
                "papatya"
        ));
        Random random = new Random();
        int randomName = random.nextInt(50);
        return name.get(randomName);
    }
}