package com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.version_1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.sargiskh.android_training_java_kotlin.R;
import com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.Employee;
import com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.EmployeeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Async_Task_Loader_Version_1 extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Employee>> {

    private EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_async_task_loader);

        employeeAdapter = new EmployeeAdapter(this, new ArrayList<Employee>());

        ListView employeeListView = (ListView)findViewById(R.id.employees);
        employeeListView.setAdapter(employeeAdapter);

        getSupportLoaderManager().initLoader(1, null,this).forceLoad();
    }

    @NonNull
    @Override
    public Loader<List<Employee>> onCreateLoader(int id, @Nullable Bundle args) {
        return new EmployeeLoader(MainActivity_Async_Task_Loader_Version_1.this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Employee>> loader, List<Employee> data) {
        employeeAdapter.setEmployees(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Employee>> loader) {
        employeeAdapter.setEmployees(new ArrayList<Employee>());
    }

}
