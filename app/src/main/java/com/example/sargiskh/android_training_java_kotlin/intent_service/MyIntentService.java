package com.example.sargiskh.android_training_java_kotlin.intent_service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

public class MyIntentService extends IntentService {

    private static final int SLEEP_TIME = 2000;

    public MyIntentService() {
        super("MyIntentService");
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("LOG_TAG_2", "onHandleIntent");

        // Extract the receiver passed into the service
        ResultReceiver resultReceiver = intent.getParcelableExtra("receiver");
        // To send a message to the Activity, create a pass a Bundle
        String val = intent.getStringExtra("key");

        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // To send a message to the Activity, create a pass a Bundle
        Bundle bundle = new Bundle();
        bundle.putString("resultValue", "My Result Value. Passed in: " + val);
        // Here we call send passing a resultCode and the bundle of extras
        resultReceiver.send(Activity.RESULT_OK, bundle);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("LOG_TAG_2", "onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("LOG_TAG_2", "onDestroy");
    }
}
