package com.winterbe.java8.samples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ConcurrentUtils {

    public static void stop(ExecutorService executor) {
        try (var ignored = executor) {
            executor.shutdown();
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("killing non-finished tasks");
            }
        } catch (InterruptedException e) {
            System.err.println("termination interrupted");
            Thread.currentThread().interrupt();
        }
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}