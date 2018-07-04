package com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.version_1;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.sargiskh.android_training_java_kotlin.async_task_loader.java.Employee;

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

        return list;
    }
}
