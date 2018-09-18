package com.example.executor;

import java.util.Random;

public class RandomClass {

    private static final String characters = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static String getRandomString(int length) {
        if (length < 0 || length > 10) {
            return "Random";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(36);
            stringBuilder.append(characters.charAt(index));
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        return stringBuilder.toString();
    }

}
