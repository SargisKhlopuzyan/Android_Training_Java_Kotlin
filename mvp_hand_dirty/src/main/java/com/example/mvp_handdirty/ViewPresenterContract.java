package com.example.mvp_handdirty;

public class ViewPresenterContract {

    public interface PresenterInterface {
        void updateFullName(String fullName) ;
        void updateEmail(String email);
    }

    public interface ViewInterface {
        void updateUserInfoTextView(String info);
        void showProgressBar();
        void hideProgressBar();
    }

}
