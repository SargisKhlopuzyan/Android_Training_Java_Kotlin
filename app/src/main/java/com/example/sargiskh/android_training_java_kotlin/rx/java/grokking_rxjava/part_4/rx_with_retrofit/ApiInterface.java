package com.example.sargiskh.android_training_java_kotlin.rx.java.grokking_rxjava.part_4.rx_with_retrofit;


import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {

    /*
    @GET("/photos")
    Call<List<Category>> getAllPhoto();
    */

    @GET("/photos")
    Observable<List<Category>> getAllPhoto();

}