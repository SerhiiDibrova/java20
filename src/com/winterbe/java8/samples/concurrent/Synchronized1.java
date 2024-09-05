package com.winterbe.java8.samples.concurrent;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Synchronized1 {

    private static final int NUM_INCREMENTS = 10000;

    private static volatile int count = 0;

    public static void main(String[] args) {
        testSyncIncrement();
        testNonSyncIncrement();
    }

    private static void testSyncIncrement() {
        count = 0;

        var executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(Synchronized1::incrementSync));

        ConcurrentUtils.stop(executor);

        System.out.println("   Sync: " + count);
    }

    private static void testNonSyncIncrement() {
        count = 0;

        var executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(Synchronized1::increment));

        ConcurrentUtils.stop(executor);

        System.out.println("NonSync: " + count);
    }

    private static void incrementSync() {
        synchronized (Synchronized1.class) {
            count++;
        }
    }

    private static void increment() {
        count++;
    }
}