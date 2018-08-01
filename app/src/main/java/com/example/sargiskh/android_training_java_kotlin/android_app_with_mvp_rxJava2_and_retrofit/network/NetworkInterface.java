package com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.network;

import com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.models.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {
    @GET("discover/movie")
    Observable<MovieResponse> getMovies(@Query("api_key") String api_key);
}
