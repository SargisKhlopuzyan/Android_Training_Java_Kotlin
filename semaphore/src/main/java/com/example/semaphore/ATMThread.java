package com.example.semaphore;

import android.util.Log;

public class ATMThread extends  Thread{

    String name = "";

    ATMThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {

            Log.e("LOG_TAG",name + " : acquiring lock..." + " : available Semaphore permits now: " + MainActivity.semaphore.availablePermits());
            MainActivity.semaphore.acquire();
            Log.e("LOG_TAG",name + " : got the permit!");

            for (int i = 1; i <= 2; i++) {
                Log.e("LOG_TAG",name + " : is performing operation " + i + ", available Semaphore permits : " + MainActivity.semaphore.availablePermits());
                // sleep 1 second
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // calling release() after a successful acquire()
            Log.e("LOG_TAG",name + " : releasing lock...");
            MainActivity.semaphore.release();
            Log.e("LOG_TAG",name + " : available Semaphore permits now: " + MainActivity.semaphore.availablePermits());
        }
    }
}
