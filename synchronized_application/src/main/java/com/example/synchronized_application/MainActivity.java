package com.example.synchronized_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartAsyncMethod(View view) {
        runThreadForSynchronizedMethod();
    }

    public void StartAsyncBlock(View view) {
        runThreadForSynchronizedBlock();
    }

    private void runThreadForSynchronizedMethod() {
        SynchronizedMethodClass example = new SynchronizedMethodClass();

        Thread thread1 = new Thread(new RunnableForSynchronizedMethod(example, "1"));
        Thread thread2 = new Thread(new RunnableForSynchronizedMethod(example, "2"));
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("LOG_TAG","Numbers: " + example.getNumbers());
    }

    private void runThreadForSynchronizedBlock() {
        SynchronizedBlockClass example = new SynchronizedBlockClass();

        Thread thread1 = new Thread(new RunnableForSynchronizedBlock(example, "1"));
        Thread thread2 = new Thread(new RunnableForSynchronizedBlock(example, "2"));
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.e("LOG_TAG","Numbers: " + example.getNumbers());

    }

}
