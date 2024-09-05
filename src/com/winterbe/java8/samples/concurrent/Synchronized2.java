package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Synchronized2 {

    private static final int NUM_INCREMENTS = 10000;

    private static int count = 0;

    public static void main(String[] args) {
        testSyncIncrement();
    }

    private static void testSyncIncrement() {
        count = 0;

        var executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(Synchronized2::incrementSync));

        executor.shutdown();

        System.out.println(count);
    }

    private static synchronized void incrementSync() {
        count++;
    }
}