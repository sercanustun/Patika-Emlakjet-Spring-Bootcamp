package com.emlakjet.rabbitmqconsumer.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomTitle {
    public static String generateTitle() {
        ArrayList<String> title1 = new ArrayList<>(Arrays.asList("satılık", "kiralık", "temiz", "doktordan", "ihtiyactan"));
        ArrayList<String> title2 = new ArrayList<>(Arrays.asList("ev", "araba", "villa", "arsa"));
        Random random = new Random();
        int titlePart1 = random.nextInt(title1.size());
        int titlePart2 = random.nextInt(title2.size());
        return title1.get(titlePart1) + "-" + title2.get(titlePart2);
    }
}