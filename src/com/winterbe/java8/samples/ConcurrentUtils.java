package com.winterbe.java8.samples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConcurrentUtils {

    private static final Logger LOGGER = Logger.getLogger(ConcurrentUtils.class.getName());

    public static void stop(ExecutorService executor) {
        if (executor == null) {
            throw new NullPointerException("Executor cannot be null");
        }
        try {
            executor.shutdown();
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                LOGGER.log(Level.SEVERE, "Killing non-finished tasks");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static void sleep(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("Sleep time cannot be negative");
        }
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}