package com.example.dagger_2.dagger;

import com.example.dagger_2.MainActivity;
import com.example.dagger_2.ProductListener;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ShoppingCartModule.class
        }
)

public interface ApplicationComponent {

    void inject(ProductListener presenter);

    void inject(MainActivity activity);

}
