package com.onlyfullstack.lambdas;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Lambda {

    public static void main(String[] args) {
        IConnection connection = () -> {System.out.println("Implementing the connect method");};
        
        connection.connect();
        connection.print();
        IConnection.description();
        simpleConceptWithRunnable();
    }
    
    private static void simpleConceptWithRunnable() {
        Runnable runnable = () -> System.out.println("Inside lambda expression");
        
        Thread thread = new Thread(runnable);
        thread.start();
        
        Consumer<String> singleParam = (param) -> System.out.printf("Hello %s\n", param);
        singleParam.accept("Saurabh");
        
        BiConsumer<String, String> doubleParam = (param1, param2) -> System.out.printf("param1 : %s, param2: %s\n", param1, param2);
        doubleParam.accept("OnlyFullstack", "Development");
    }

    @FunctionalInterface
    interface IConnection {
        void connect();
        default void print() {System.out.println("Printing from interface");};
        static void description() {System.out.println("Description of the connection");};
    }
}