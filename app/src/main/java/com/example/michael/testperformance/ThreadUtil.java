package com.example.michael.testperformance;

/**
 * Created by michael on 16-12-26.
 */

public class ThreadUtil {
    public static void sleepAWhile(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
