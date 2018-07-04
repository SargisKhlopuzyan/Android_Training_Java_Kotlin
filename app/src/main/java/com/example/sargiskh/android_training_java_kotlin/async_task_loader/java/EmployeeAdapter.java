package com.example.sargiskh.android_training_java_kotlin.async_task_loader.java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sargiskh.android_training_java_kotlin.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Employee> employees = new ArrayList<Employee>();

    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.inflater = LayoutInflater.from(context);
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Employee employee = (Employee)getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.employee_data, null);
        }

        TextView empid = convertView.findViewById(R.id.empid);
        TextView empname = convertView.findViewById(R.id.empname);

        empid.setText(employee.empid);
        empname.setText(employee.name);

        return convertView;
    }

    public void setEmployees(List<Employee> data) {
        employees.clear();
        employees.addAll(data);
        notifyDataSetChanged();
    }
    
}
