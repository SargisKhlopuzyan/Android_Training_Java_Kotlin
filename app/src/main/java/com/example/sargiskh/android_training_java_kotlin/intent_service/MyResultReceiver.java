package com.example.sargiskh.android_training_java_kotlin.intent_service;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

public class MyResultReceiver extends ResultReceiver {

    interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

    private Receiver receiver;

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    @SuppressLint("RestrictedApi")
    public MyResultReceiver(Handler handler) {
        super(handler);
        Log.e("LOG_TAG", "MyResultReceiver");
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        Log.e("LOG_TAG", "onReceiveResult");
        if (receiver != null) {
            receiver.onReceiveResult(resultCode, resultData);
        }
    }

}
