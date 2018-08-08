package com.example.mvp_with_dagger.dagger;

import com.example.mvp_with_dagger.view.MainActivity;
import com.example.mvp_with_dagger.ProductListener;
import com.example.mvp_with_dagger.presenter.ProductListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                ShoppingCartModule.class,
                PersistenceModule.class
        }
)

public interface ApplicationComponent {

    void inject(ProductListener presenter);

    void inject(MainActivity activity);

    void inject(ProductListPresenter presenter);

}
