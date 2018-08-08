package com.example.mvp_with_dagger.dagger;

import android.content.Context;

import com.example.mvp_with_dagger.ProductListContract;
import com.example.mvp_with_dagger.TempRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class PersistenceModule {

    @Provides
    public ProductListContract.Repository providesProductRepository(Context context) {
        return new TempRepository();
    }

}
