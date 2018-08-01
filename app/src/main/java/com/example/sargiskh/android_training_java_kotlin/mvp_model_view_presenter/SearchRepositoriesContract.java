package com.example.sargiskh.android_training_java_kotlin.mvp_model_view_presenter;

import com.example.sargiskh.android_training_java_kotlin.mvp_model_view_presenter.model.Repository;
import com.example.sargiskh.android_training_java_kotlin.mvp_model_view_presenter.presenter.BasePresenter;

import java.util.List;

public interface SearchRepositoriesContract {

    interface View {
        void addResults(List<Repository> repos);
        void clearResults();
        void showContentLoading();
        void hideContentLoading();
        void showListLoading();
        void hideListLoading();
        void showContentError();
        void hideContentError();
        void showListError();
        void showEmptyResultsView();
        void hideEmptyResultsView();
    }

    interface Presenter extends BasePresenter<View> {

        //Actions
        void load();
        void loadMore();

        //User events
        void queryChanged(String query);
        void repositoryClick(Repository repo);
    }

}
