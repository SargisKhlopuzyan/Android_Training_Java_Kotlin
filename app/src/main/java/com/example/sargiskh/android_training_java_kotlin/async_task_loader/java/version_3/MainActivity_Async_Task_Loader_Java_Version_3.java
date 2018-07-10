package com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.version_3;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sargiskh.android_training_java_kotlin.R;
import com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.Employee;
import com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.EmployeeAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity_Async_Task_Loader_Java_Version_3 extends AppCompatActivity {

    private EmployeeAdapter employeeAdapter;

    private ProgressBar progressBar;
    private ListView employeeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_async_task_loader_java_3);

        progressBar = findViewById(R.id.progressBar);
        employeeListView = (ListView)findViewById(R.id.employees);

        employeeAdapter = new EmployeeAdapter(this, new ArrayList<Employee>());
        employeeListView.setAdapter(employeeAdapter);

        EmployeeLoader_version_3 employeeLoader_version_3 = new EmployeeLoader_version_3(this);
        employeeLoader_version_3.forceLoad();
    }

    private static class EmployeeLoader_version_3 extends AsyncTaskLoader<List<Employee>> {

        private static final int SLEEP_TIME = 1000;
        private static final int MAX_COUNT = 20;

        static WeakReference<MainActivity_Async_Task_Loader_Java_Version_3> mActivity;

        public EmployeeLoader_version_3(MainActivity_Async_Task_Loader_Java_Version_3 activity) {
            super(activity);
            mActivity = new WeakReference<MainActivity_Async_Task_Loader_Java_Version_3>(activity);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            Log.e("LOG_TAG", "v2: onStartLoading");

            mActivity.get().progressBar.setProgress(0);
            mActivity.get().employeeAdapter.setEmployees(new ArrayList<Employee>());
        }

        @Override
        public List<Employee> loadInBackground() {
            Log.e("LOG_TAG", "v2: loadInBackground");

            final List<Employee> list = new ArrayList<Employee>();

            for (int i = 0; i < MAX_COUNT; i++) {
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                list.add(new Employee("emp" + i, "Brahma_" + i));

                final int progress = (100*(i+1)) / MAX_COUNT;
                if (mActivity.get() != null) {
                    mActivity.get().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mActivity.get().progressBar.setProgress(progress);
                            mActivity.get().employeeAdapter.setEmployees(list);
                        }
                    });
                }
            }

//            list.add(new Employee("emp1", "Brahma"));
//            list.add(new Employee("emp2", "Vishnu"));
//            list.add(new Employee("emp3", "Mahesh"));

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

        @Override
        public void deliverResult(List<Employee> data) {
            super.deliverResult(data);
            Log.e("LOG_TAG", "v2: deliverResult");

            mActivity.get().progressBar.setProgress(100);
            mActivity.get().employeeAdapter.setEmployees(data);
        }
    }

}
