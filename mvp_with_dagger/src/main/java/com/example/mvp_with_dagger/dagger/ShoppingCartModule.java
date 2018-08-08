package com.example.mvp_with_dagger.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mvp_with_dagger.ShoppingCart;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ShoppingCartModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    ShoppingCart provideShoppingCart(SharedPreferences preferences) {
        return new ShoppingCart(preferences);
    }

}
