package com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.version_2;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.sargiskh.android_training_java_kotlin.R;
import com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.Employee;
import com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.EmployeeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Async_Task_Loader_Version_2 extends AppCompatActivity {

    private EmployeeAdapter employeeAdapter;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_async_task_loader);

        employeeAdapter = new EmployeeAdapter(this, new ArrayList<Employee>());

        ListView employeeListView = (ListView)findViewById(R.id.employees);
        employeeListView.setAdapter(employeeAdapter);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                List<Employee> data = (List<Employee>)msg.obj;
                employeeAdapter.setEmployees(data);
            }
        };


        EmployeeLoader_version2 employeeLoader_version2 = new EmployeeLoader_version2(this);
        employeeLoader_version2.forceLoad();
    }

    private class EmployeeLoader_version2 extends AsyncTaskLoader<List<Employee>> {

        public EmployeeLoader_version2(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            Log.e("LOG_TAG", "v2: onStartLoading");
            Message message = new Message();
            message.obj = new ArrayList<Employee>();
            handler.sendMessage(message);
        }

        @Override
        public List<Employee> loadInBackground() {

            Log.e("LOG_TAG", "v2: loadInBackground");
            List<Employee> list = new ArrayList<Employee>();
            list.add(new Employee("emp1", "Brahma"));
            list.add(new Employee("emp2", "Vishnu"));
            list.add(new Employee("emp3", "Mahesh"));

            return list;
        }

        @Override
        public void deliverResult(List<Employee> data) {
            super.deliverResult(data);
            Log.e("LOG_TAG", "v2: deliverResult");
            Message message = new Message();
            message.obj = data;
            handler.sendMessage(message);
        }
    }

}
