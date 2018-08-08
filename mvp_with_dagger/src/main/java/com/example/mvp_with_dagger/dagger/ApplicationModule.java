package com.example.mvp_with_dagger.dagger;

import android.content.Context;

import com.example.mvp_with_dagger.ShopApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Modules in Dagger 2 contain methods which are essentially advanced switch statements
 */
@Module
public class ApplicationModule {

    private final ShopApplication application;

    public ApplicationModule(ShopApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }
}
