package com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.version_1;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EmployeeLoader extends AsyncTaskLoader<List<Employee>> {

    public EmployeeLoader(Context context) {
        super(context);
    }

    @Override
    public List<Employee> loadInBackground() {

        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee("emp1", "Brahma"));
        list.add(new Employee("emp2", "Vishnu"));
        list.add(new Employee("emp3", "Mahesh"));

        /**
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String jsonStr = null;
        String line;

        try {
            URL url = new URL("https://itunes.apple.com/search?term=classic");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            if (buffer.length() == 0) {
                return null;
            }

            jsonStr = buffer.toString();

        } catch (MalformedURLException e) {
            Log.e("MainActivity", "Error : MalformedURLException", e);
            return null;
        } catch (IOException e) {
            Log.e("MainActivity", "Error : IOException", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e("LOG_TAG", "Error closing stream", e);
                    }
                }
            }
        }

        Log.e("LOG_TAG", "jsonStr: " + jsonStr);
        */

        return list;
    }
}
