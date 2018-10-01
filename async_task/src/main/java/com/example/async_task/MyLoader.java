package com.example.async_task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MyLoader extends Loader<String> {

    final String LOG_TAG = "LOG_TAG";
    final int PAUSE = 10;

    public final static String ARGS_TIME_FORMAT = "time_format";
    public final static String TIME_FORMAT_SHORT = "h:mm:ss a";
    public final static String TIME_FORMAT_LONG = "yyyy.MM.dd G 'at' HH:mm:ss";

    MyAsyncTask myAsyncTask;
    String format;

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        Log.d(LOG_TAG, hashCode() + " create MyLoader");
        if (args != null)
            format = args.getString(ARGS_TIME_FORMAT);
        if (TextUtils.isEmpty(format))
            format = TIME_FORMAT_SHORT;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(LOG_TAG, hashCode() + " onStartLoading");
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.d(LOG_TAG, hashCode() + " onStopLoading");
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        Log.d(LOG_TAG, hashCode() + " onForceLoad");
        if (myAsyncTask != null)
            myAsyncTask.cancel(true);
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, format);
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        Log.d(LOG_TAG, hashCode() + " onAbandon");
    }

    @Override
    protected void onReset() {
        super.onReset();
        Log.d(LOG_TAG, hashCode() + " onReset");
    }

    private class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            Log.d(LOG_TAG, MyLoader.this.hashCode() + " doInBackground");
            try {
                TimeUnit.SECONDS.sleep(PAUSE);
            } catch (InterruptedException e) {
                return null;
            }

            SimpleDateFormat sdf = new SimpleDateFormat(strings[0], Locale.getDefault());
            return sdf.format(new Date());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(LOG_TAG, MyLoader.this.hashCode() + " onPostExecute " + s);
            deliverResult(s);
        }

    }

}