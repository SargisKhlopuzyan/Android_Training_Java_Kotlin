package com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.ui.presenter;

import android.util.Log;

import com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.models.MovieResponse;
import com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.network.NetworkClient;
import com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.network.NetworkInterface;
import com.example.sargiskh.android_training_java_kotlin.android_app_with_mvp_rxJava2_and_retrofit.ui.MainViewInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface {

    private static final String API_KEY = "85e1b41e97a067cbae5f2242dad4e3d0";
    private static final String TAG = "LOG_TAG";

    MainViewInterface mainViewInterface;

    public MainPresenter(MainViewInterface mainViewInterface) {
        this.mainViewInterface = mainViewInterface;
    }

    @Override
    public void getMovies() {
        getObservable().subscribe(getObserver());
    }

    private Observable<MovieResponse> getObservable() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieResponse> getObserver() {

        return new DisposableObserver<MovieResponse>() {

            @Override
            public void onNext(MovieResponse movieResponse) {
                Log.e(TAG, "OnNext" + movieResponse.getTotalResults());
                mainViewInterface.displayMovies(movieResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"Error"+e);
                e.printStackTrace();
                mainViewInterface.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.e(TAG,"Completed");
            }
        };
    }
}
