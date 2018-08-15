package com.example.producerconsumer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Iterator;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private static Vector<Object> data = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Producer("Producer1").start();
        new Consumer("Consumer1").start();
        new Producer("Producer2").start();
        new Consumer("Consumer2").start();
    }


    public static class Consumer extends Thread {

        public Consumer(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (;;) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized(data) {
                    if (data.size() > 0) {
                        data.remove(0);
                        Log.e("LOG_TAG", getName() + " : Object Consumed ################");
                    }

//                    Iterator iterator = data.iterator();
//                    if (iterator.hasNext()) {
//                        iterator.next();
//                        iterator.remove();
//                        Log.e("LOG_TAG", "Object Consumed ################");
//                    }

//                    while (iterator.hasNext()) {
//                        Log.e("LOG_TAG", "iterator.next");
//                        iterator.next();
//                    }
                }
            }
        }
    }

    public static class Producer extends Thread {

        public Producer( String name ) {
            super(name);
        }

        @Override
        public void run() {
            for (;;) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (data) {
                    if (data.size() < 10) {
                        Log.e("LOG_TAG", getName() + " : Object Produced ~~~~~~~~~~~~~~~ : " + data.size());
                        data.add(new Object());
                    }
                }

//                data.add(new Object());
//                if (data.size() > 10) {
//                    data.remove(data.size() - 1);
//                }
            }
        }
    }

}
