package com.example.sargiskh.android_training_java_kotlin.retrofit.java;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.sargiskh.android_training_java_kotlin.R;
import com.example.sargiskh.android_training_java_kotlin.retrofit.java.network.RetrofitClientInstance_Java;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity_Java extends AppCompatActivity {

    private Adapter_Java adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_java);

        progressDialog = new ProgressDialog(RetrofitActivity_Java.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        /*Create handle for the RetrofitInstance interface*/
        ApiInterface service = RetrofitClientInstance_Java.getRetrofitInstance().create(ApiInterface.class);
        Call<List<Category>> call = service.getAllPhoto();

        call.enqueue(new Callback<List<Category>>() {

            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RetrofitActivity_Java.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Category> photoList) {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter_Java(this, photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RetrofitActivity_Java.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
