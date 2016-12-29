package com.example.michael.testperformance;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by michael on 16-12-26.
 */

public class MemoryUtil {
    public static void printMemory() {
        long memoryBytes = Runtime.getRuntime().totalMemory();
        
        Log.d("performanceMemory", "memoryMBs = " + (memoryBytes / 1024 / 1024) + " MBs");
    }

    public static void printMemoryLoop() {
        Timer timer = new Timer("printMemoryThread", true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                printMemory();
            }
        };
        timer.schedule(task, 2 * 1000, 1 * 1000);
    }





}
