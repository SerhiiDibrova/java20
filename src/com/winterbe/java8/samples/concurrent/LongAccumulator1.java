package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

public class LongAccumulator1 {

    public static void main(String[] args) {
        testAccumulate();
    }

    private static void testAccumulate() {
        var op = (x, y) -> 2 * x + y;
        var accumulator = new LongAccumulator(op, 1L);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10)
                .forEach(i -> executor.submit(() -> accumulator.accumulate(i)));

        executor.shutdown();

        System.out.format("Add: %d\n", accumulator.getThenReset());
    }
}