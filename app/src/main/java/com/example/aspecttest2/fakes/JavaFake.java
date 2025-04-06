package com.example.aspecttest2.fakes;


public class JavaFake {

    // Simulate long initialization
    public JavaFake() {
        System.out.println("JavaFake.constructor");
        try {
            Thread.sleep(777);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doFoo() {
        System.out.println("JavaFake.foo");
    }
}
