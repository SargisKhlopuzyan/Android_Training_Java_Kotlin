package com.example.sargiskh.android_training_java_kotlin.retrofit.java;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/photos")
    Call<List<Category>> getAllPhoto();

}
