package com.winterbe.java8.samples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ConcurrentUtils {

    public static void stop(ExecutorService executor) {
        if (executor == null) {
            throw new NullPointerException("executor");
        }
        try {
            var terminated = executor.shutdown();
            if (!terminated.awaitTermination(60, TimeUnit.SECONDS)) {
                System.err.println("killing non-finished tasks");
            }
        } catch (InterruptedException e) {
            System.err.println("termination interrupted");
        } finally {
            executor.shutdownNow();
        }
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}