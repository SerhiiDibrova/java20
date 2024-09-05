package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Lock2 {

    public static void main(String[] args) {
        var executor = Executors.newFixedThreadPool(2);
        var lock = new ReentrantLock();

        executor.submit(() -> {
            lock.lock();
            try {
                Thread.sleep(1000); // replaced ConcurrentUtils.sleep with Thread.sleep
            } finally {
                lock.unlock();
            }
        });

        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            var locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        executor.shutdown(); // replaced ConcurrentUtils.stop with executor.shutdown
    }
}