package com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.ui;

import com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.models.MovieResponse;

public interface MainViewInterface {
    void showToast(String s);
    void displayMovies(MovieResponse movieResponse);
    void displayError(String s);
}
