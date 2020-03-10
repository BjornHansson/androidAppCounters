package se.bjornhansson.counters;

import java.util.Random;

/**
 * Used to start a new game. Holds the target number.
 */
public class Game {

    private final int myTargetNumber = getRandomNumberInRange(100, 999);

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public int getMyTargetNumber() {
        return myTargetNumber;
    }
}
