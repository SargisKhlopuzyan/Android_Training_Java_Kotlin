package com.example.synchronized_application.inter_thread_communication;

import android.util.Log;

public class ThreadExample {

    public static void startProduceConsume() throws InterruptedException {

        final ProduceConsume produceConsume =  new ProduceConsume();

        // Create a thread object that calls pc.produce()
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produceConsume.produce();
                } catch (InterruptedException e) {
                    Log.e("LOG_TAG", "Produce -> InterruptedException: " + e);
                }
            }
        });

        // Create another thread object that calls pc.consume(
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produceConsume.consume();
                } catch (InterruptedException e) {
                    Log.e("LOG_TAG", "Consume -> InterruptedException: " + e);
                }
            }
        });

        // Start both threads
        thread1.start();
        thread2.start();

        // thread1 finishes before thread2
        thread1.join();
        thread2.join();
    }

}
