package com.example.mvc_eventbus_retrofit.mvc_model;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.mvc_eventbus_retrofit.network.NetworkManager;

public class ItemPhotosModel {

    private final List<ItemPhoto> mItemPhotoList = new ArrayList<>();

    private int mItemCount = 0;

    private boolean mIsBusy = false;

    public ItemPhotosModel() {}

    public List<ItemPhoto> getItemPhotosList() {
        return mItemPhotoList;
    }

    public int getItemCount() {
        return mItemCount;
    }

    public void load() {
        if (mIsBusy) {
            return;
        }

        mIsBusy = true;
        getPhotos();
    }

    private void getPhotos() {

        /*Create handle for the RetrofitInstance interface*/
        NetworkManager.ApiService service = NetworkManager.getRetrofitInstance().create(NetworkManager.ApiService.class);

        Call<List<ItemPhoto>> call = service.getPhotos();

        call.enqueue(new Callback<List<ItemPhoto>>() {

            @Override
            public void onResponse(Call<List<ItemPhoto>> call, Response<List<ItemPhoto>> response) {

                List<ItemPhoto> result = response.body();

                mItemPhotoList.clear();
                mItemPhotoList.addAll(result);
                mItemCount = result.size();
                mIsBusy = false;

                // EventBus
                EventBus.getDefault().post(new VideosLoadedEvent(true));
            }

            @Override
            public void onFailure(Call<List<ItemPhoto>> call, Throwable t) { }

        });
    }

    public static class VideosLoadedEvent {

        private boolean isSuccess;

        private VideosLoadedEvent(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public boolean isSuccess() {
            return isSuccess;
        }
    }

}
