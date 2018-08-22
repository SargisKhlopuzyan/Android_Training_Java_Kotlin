package com.example.synchronized_application.geeksforgeeks;

import android.util.Log;

import java.util.concurrent.TimeUnit;

public class Sender {

    public void showMessage(String msg) {
        Log.e("LOG_TAG","Sending " + msg);
        try {
//            Thread.sleep(1000)
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Log.e("LOG_TAG","Thread  interrupted. " + e);
        }
        Log.e("LOG_TAG",msg + " Sent");
    }

}
