```java
package com.winterbe.java20.samples.concurrent;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Executors3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        test1();
//        test2();
//        test3();

//        test4();
//        test5();
    }

    private static void test5() throws InterruptedException, ExecutionException {
        var executor = Executors.newWorkStealingPool();

        var callables = List.of(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3));

        try (executor) {
            var result = executor.invokeAny(callables);
            System.out.println(result);
        }
    }

    private static Callable<String> callable(String result, long sleepSeconds) {
        return () -> {
            Thread.sleep(sleepSeconds * 1000);
            return result;
        };
    }

    private static void test4() throws InterruptedException {
        var executor = Executors.newWorkStealingPool();

        var callables = List.of(
                (Callable<String>) () -> "task1",
                (Callable<String>) () -> "task2",
                (Callable<String>) () -> "task3");

        try (executor) {
            executor.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .forEach(System.out::println);
        }
    }

    private static void test3() {
        var executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("Scheduling: " + System.nanoTime());
            } catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        try (executor) {
            executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
        }
    }

    private static void test2() {
        var executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        int initialDelay = 0;
        int period = 1;
        try (executor) {
            executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
        }
    }

    private static void test1() throws InterruptedException {
        var executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        int delay = 3;
        try (var future = executor.schedule(task, delay, TimeUnit.SECONDS)) {

            Thread.sleep(1337);

            long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
            System.out.printf("Remaining Delay: %sms\n", remainingDelay);
        }
    }

}
```