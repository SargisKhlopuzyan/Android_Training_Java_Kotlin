package com.example.mvc_eventbus_retrofit;

import android.app.Application;

import com.example.mvc_eventbus_retrofit.mvc_controller.FragmentRouter;
import com.example.mvc_eventbus_retrofit.mvc_controller.ModeLocator;
import com.example.mvc_eventbus_retrofit.mvc_view.DetailFragment;
import com.example.mvc_eventbus_retrofit.mvc_view.ListFragment;
import com.example.mvc_eventbus_retrofit.mvc_model.ItemPhotosModel;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // ModelManager
        ModeLocator.register(ModeLocator.Tag.ITEM_PHOTOS, new ItemPhotosModel());

        // FragmentManager Fragment
        FragmentRouter.register(FragmentRouter.Tag.LIST, ListFragment.class);
        FragmentRouter.register(FragmentRouter.Tag.DETAIL, DetailFragment.class);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
