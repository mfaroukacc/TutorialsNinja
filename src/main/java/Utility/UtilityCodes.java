package Utility;


import java.util.Random;

public class UtilityCodes {
    public static int generateRandomInt() {
        Random random = new Random();
        return random.nextInt(8) + 1;

    }
}