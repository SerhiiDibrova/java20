package com.winterbe.java8.samples.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lock3 {

    public static void main(String[] args) {
        var executor = Executors.newFixedThreadPool(2);

        var map = new HashMap<String, String>();

        var lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            lock.writeLock().lock();
            try {
                Thread.sleep(1000);
                map.put("foo", "bar");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.writeLock().unlock();
            }
        });

        var readTask = () -> {
            lock.readLock().lock();
            try {
                System.out.println(map.getOrDefault("foo", "default"));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.readLock().unlock();
            }
        };
        executor.submit(readTask);
        executor.submit(readTask);

        executor.shutdownNow();
    }

}