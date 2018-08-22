package com.example.synchronized_application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.synchronized_application.geeksforgeeks.Sender;
import com.example.synchronized_application.geeksforgeeks.ThreadedSend;
import com.example.synchronized_application.inter_thread_communication.ThreadExample;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAsyncMethod(View view) {
        runThreadForSynchronizedMethod();
    }

    public void startAsyncBlock(View view) {
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

    public void startWait(View view) {
        Thread thread = new Thread(new WaitAndNotifyClass());
        thread.start();
    }

    public void lock(View view) {
        WaitAndNotifyClass.lock();
    }

    public void unlock(View view) {
        WaitAndNotifyClass.unlock();
    }

    public void geeksForGeeks(View view) {

        Sender sender = new Sender();
        ThreadedSend threaded1 = new ThreadedSend( " Hi " , sender );
        ThreadedSend threaded2 = new ThreadedSend( " Bye " , sender );

        // Start two threads of ThreadedSend type
        threaded1.start();
        threaded2.start();
        // wait for threads to end
        try {
            threaded1.join();
            threaded2.join();
        } catch(Exception e) {
            Log.e("LOG_TAG","Interrupted");
        }

    }

    public void startProduceConsume(View view) {
        try {
            ThreadExample.startProduceConsume();
        } catch (InterruptedException e) {
            Log.e("LOG_TAG", "InterruptedException: " + e);
        }
    }
}
