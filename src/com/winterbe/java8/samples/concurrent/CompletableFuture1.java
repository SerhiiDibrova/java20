package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFuture1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var future = new CompletableFuture<String>();
        
        future.complete("42");

        future
                .thenAccept(System.out::println)
                .thenAccept(v -> System.out.println("done"));
    }
}