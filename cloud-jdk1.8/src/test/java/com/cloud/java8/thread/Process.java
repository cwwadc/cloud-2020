package com.cloud.java8.thread;

public class Process implements Runnable {

    @Override
    public void run() {
        for (int i =0;i<100; i++ ) {
            System.out.println("第"+ i+"个线程");
        }
    }
}
