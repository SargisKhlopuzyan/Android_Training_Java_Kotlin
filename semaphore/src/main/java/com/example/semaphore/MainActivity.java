package com.example.semaphore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Semaphore;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_AVAILABLE = 4;
    public static Semaphore semaphore = new Semaphore(MAX_AVAILABLE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("LOG_TAG","Total available Semaphore permits : " + semaphore.availablePermits());

        ATMThread t1 = new ATMThread("A");
        t1.start();

        ATMThread t2 = new ATMThread("B");
        t2.start();

        ATMThread t3 = new ATMThread("C");
        t3.start();

        ATMThread t4 = new ATMThread("D");
        t4.start();

        ATMThread t5 = new ATMThread("E");
        t5.start();

        ATMThread t6 = new ATMThread("F");
        t6.start();
    }
}