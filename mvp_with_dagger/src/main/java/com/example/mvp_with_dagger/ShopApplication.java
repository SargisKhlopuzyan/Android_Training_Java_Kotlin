package com.example.mvp_with_dagger;


import android.app.Application;

import com.example.mvp_with_dagger.dagger.ApplicationComponent;
import com.example.mvp_with_dagger.dagger.ApplicationModule;
import com.example.mvp_with_dagger.dagger.DaggerApplicationComponent;

public class ShopApplication extends Application {

    private static ShopApplication instance = new ShopApplication();
    private static ApplicationComponent applicationComponent;

    public static ShopApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getApplicationComponent();
    }

    public ApplicationComponent getApplicationComponent() {
        if(applicationComponent == null) {
            // DaggerApplicationComponent created automatically !!!!!
            applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        }
        return applicationComponent;
    }

}
