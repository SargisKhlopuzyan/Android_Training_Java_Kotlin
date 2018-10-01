package com.example.executor;

import android.util.Log;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class StringPrinter_With_ScheduledThreadPoolExecutor {

    private final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3);

    public void startScheduledThreadPoolExecuter() {
        Log.e("LOG_TAG", "ScheduledThreadPoolExecutor starts");
//        scheduledThreadPoolExecutor.schedule(getRunnable(1), 10, TimeUnit.SECONDS);
//        scheduledThreadPoolExecutor.scheduleAtFixedRate(getRunnable(1), 0,2, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(getRunnable(1), 0,2, TimeUnit.SECONDS);
    }

    public void shutDownExecutor() {
        scheduledThreadPoolExecutor.shutdown();
    }

    public void shutDownNowExecutor() {
        scheduledThreadPoolExecutor.shutdownNow();
    }

    int count = 0;
    private Runnable getRunnable(final int i) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String randomString = RandomClass.getRandomString(i);
                if (Thread.interrupted()) {
                    Log.e("LOG_TAG", "" + Thread.class.getName() + "is interrupted" );
                    return;
                }
                ++count;
                if (count == 3) {
                    shutDownNowExecutor();
                }
            }
        };
        return runnable;
    }
}
