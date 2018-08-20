package com.example.crunchify.retry_n_times;

import android.util.Log;

public class RetryOnExceptionStrategy {

    private static final int DEFAULT_RETRIES = 3;
    private static final long DEFAULT_WAIT_TIME_IN_MILLI = 2000;

    private int numberOfRetries;
    private int numberOfTriesLeft;
    private long timeToWait;

    public RetryOnExceptionStrategy() {
        this(DEFAULT_RETRIES, DEFAULT_WAIT_TIME_IN_MILLI);
    }

    public RetryOnExceptionStrategy(int numberOfRetries, long timeToWait) {
        this.numberOfRetries = numberOfRetries;
        this.numberOfTriesLeft = numberOfRetries;
        this.timeToWait = timeToWait;
    }

    /**
     * @return true if there are tries left
     */
    public boolean shouldRetry() {
        return numberOfTriesLeft > 0;
    }

    public void stopRetry() {
        numberOfRetries = 0;
        numberOfTriesLeft = 0;
    }

    public void errorOccurred() throws Exception {
        numberOfTriesLeft--;
        if (!shouldRetry()) {
            Log.e("LOG_TAG", "Retry Failed: Total " + numberOfRetries + " attempts made at interval " + timeToWait + "ms");
        }
        waitUntilNextTry();
    }

    private void waitUntilNextTry() {
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException ignored) {

        }
    }

}
