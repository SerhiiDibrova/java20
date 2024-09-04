package com.onlyfullstack.lambdas;

import java.util.Optional;

public class Lambda {

    public static void main(String[] args) {
        IConnection connection = () -> System.out.println("Implementing the connect method");
        
        connection.connect();
        connection.print();
        IConnection.description();
        simpleConceptWithRunnable();
    }
    
    private static void simpleConceptWithRunnable() {
        Runnable runnable = () -> System.out.println("Inside lambda expression");
        
        Thread thread = new Thread(runnable);
        thread.start();
        
        SingleParam singleParam = (param) -> Optional.ofNullable(param).ifPresentOrElse(
            p -> System.out.printf("Hello %s\n",p), 
            () -> System.out.println("Parameter is null")
        );
        singleParam.print("saurabh");
        
        DoubleParam doubleParam = (param1, param2) -> {
            if (param1 != null && param2 != null) {
                System.out.printf("param1 : %s, param2: %s\n", param1, param2);
            } else {
                System.out.println("One or both parameters are null");
            }
        };
        doubleParam.print("OnlyFullstack", "Development");
    }

    @FunctionalInterface
    interface SingleParam {
        public void print(String param);
    }
    
    @FunctionalInterface
    interface DoubleParam {
        public void print(String param1, String param2);
    }

    @FunctionalInterface
    interface IConnection {
        default void connect() { System.out.println("Implementing the connect method"); }
        default void print() { System.out.println("Implementing the print method"); }
        static void description() { System.out.println("This is a connection"); }
    }
}