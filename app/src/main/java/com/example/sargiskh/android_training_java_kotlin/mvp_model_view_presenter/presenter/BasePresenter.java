package com.example.sargiskh.android_training_java_kotlin.mvp_model_view_presenter.presenter;

public interface BasePresenter<V> {
    void attach(V view);
    void detach();
}
