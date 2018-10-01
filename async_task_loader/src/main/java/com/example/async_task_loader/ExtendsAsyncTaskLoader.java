package com.example.async_task_loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class ExtendsAsyncTaskLoader  extends AsyncTaskLoader<String> {

    public ExtendsAsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Log.e("LOG_TAG", "onStartLoading");
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        //Think of this as AsyncTask doInBackground() method,
        // here you will actually initiate Network call,
        // or any work that need to be done on background
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        return "Loading In a Background";
    }
}