package com.example.crunchify.retry_n_times;

// https://crunchify.com/how-to-retry-operation-n-number-of-times-in-java/
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class RetryNTimes {

    private enum URLs {
        URL_NOT_WORKING("https://crunchify.com/wp-content/uploads/code/json.sample2.txt"),
        URL_WORKING("https://cdn.crunchify.com/wp-content/uploads/code/json.sample.txt");

        String urlString;

        URLs(String urlString) {
            this.urlString = urlString;
        }
    }

    public static void startDownloadingInNewThread() {
        new Thread() {
            @Override
            public void run() {
                startDownloading();
            }
        }.start();
    }

    private static void startDownloading() {

        RetryOnExceptionStrategy retry = new RetryOnExceptionStrategy();

        String urlString = URLs.URL_WORKING.urlString;

        while (retry.shouldRetry()) {
            try {
                Log.e("LOG_TAG","Requested URL:" + urlString);
                StringBuilder stringBuilder = new StringBuilder();
                HttpURLConnection urlConnection = null;
                InputStreamReader inputStreamReader = null;

                URL url = new URL(urlString);
                urlConnection = (HttpURLConnection)url.openConnection();

                if (urlConnection != null) {
                    urlConnection.setReadTimeout(60 * 1000);
                }
                if (urlConnection != null && urlConnection.getInputStream() != null) {
                    inputStreamReader = new InputStreamReader(urlConnection.getInputStream(), Charset.defaultCharset());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    if (bufferedReader != null) {
                        int cp;
                        while ((cp = bufferedReader.read()) != -1) {
                            stringBuilder.append(cp);
                        }
                    }
                }
                Log.e("LOG_TAG", stringBuilder.toString());
                inputStreamReader.close();
                retry.stopRetry();
                break;
            } catch (Exception e) {
                try {
                    Log.e("LOG_TAG","in catch..... " + e);
                    retry.errorOccurred();
                } catch (Exception e1) {
                    Log.e("LOG_TAG","Exception while calling URL: " + urlString, e);
                }
            }
        }
    }

}
