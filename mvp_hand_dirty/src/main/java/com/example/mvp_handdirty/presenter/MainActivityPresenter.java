package com.example.mvp_handdirty.presenter;

import com.example.mvp_handdirty.ViewPresenterContract;
import com.example.mvp_handdirty.ViewPresenterContract.*;
import com.example.mvp_handdirty.model.User;

public class MainActivityPresenter implements ViewPresenterContract.PresenterInterface{

    private User user;
    private ViewInterface view;

    public MainActivityPresenter(ViewInterface view) {
        user = new User();
        this.view = view;
    }

    @Override
    public void updateFullName(String fullName) {
        user.setFullName(fullName);
        view.updateUserInfoTextView(user.toString());
    }

    @Override
    public void updateEmail(String email) {
        user.setEmail(email);
        view.updateUserInfoTextView(user.toString());
    }


}
