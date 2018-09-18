package com.example.executor;

import android.support.v4.content.Loader;
import android.util.Log;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class StringPrinter_With_ThreadPoolExecutor {

    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());

    public void printString() {
        for (int i = 0; i <= 6; i++) {
            threadPoolExecutor.execute(getRunnable(i));
        }
    }

    int count = 0;

    private Runnable getRunnable(final int i) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                ++count;
                if (count == 4) {
                    shutDownNowExecutor();
                }

                if (Thread.interrupted()) {
                    Log.e("LOG_TAG", "Thread is interrupted" );
                    return;
                }

                String randomString = RandomClass.getRandomString(i);
                Log.e("LOG_TAG", "String returned is : " + randomString);

            }
        };
        return runnable;
    }

    public void shutDownExecutor() {
        threadPoolExecutor.shutdown();
    }

    public void shutDownNowExecutor() {
        threadPoolExecutor.shutdownNow();
    }

}
