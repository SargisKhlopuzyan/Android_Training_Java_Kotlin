package com.example.synchronized_application;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitAndNotifyClass implements Runnable {

    private static Object lock = new Object();

    public static void lock() {
        try {
            synchronized (lock) {
                Log.e("LOG_TAG", "lock");
                lock.wait();
            }
        } catch (InterruptedException e) {
            Log.e("LOG_TAG", "lock -> InterruptedException : " + e);
        }
    }

    public static void unlock() {
        Log.e("LOG_TAG", "unlock");
        lock.notify();
    }

    @Override
    public void run() {

        try {
            Log.e("LOG_TAG", "WaitAndNotifyClass started");
            synchronized (lock) {
                lock.wait();
                TimeUnit.SECONDS.sleep(3);
                lock.notify();
            }

            Log.e("LOG_TAG", "WaitAndNotifyClass finished");
        } catch (InterruptedException e) {
            Log.e("LOG_TAG", "InterruptedException: " + e);
        }

    }
}
