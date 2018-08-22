package com.example.synchronized_application.inter_thread_communication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ProduceConsume {

    private List<String> list = new ArrayList<>();

    public void produce() throws InterruptedException {
        synchronized (this) {
            while (true) {
                Log.e("LOG_TAG", "*produce_1* -> size: " + list.size() + " : " + Thread.currentThread().getName());
                list.add("Sargis");
                Log.e("LOG_TAG", "*produce_2* -> size: " + list.size() + " : " + Thread.currentThread().getName());
                this.notify();
                this.wait();
            }
        }
    }

    public void consume() throws InterruptedException {
        // this makes the produce thread to run first.
        Thread.sleep(2000);

        // synchronized block ensures only one thread running at a time.
        synchronized (this) {
            while (true) {
                Log.e("LOG_TAG","*consume_1* -> size: " + list.size() + " : " + Thread.currentThread().getName());
                Thread.sleep(4000);
                list.remove(0);
                Log.e("LOG_TAG","*consume_2* -> size: " + list.size() + " : " + Thread.currentThread().getName());
                this.notify();
                this.wait();
            }
        }
    }
}
