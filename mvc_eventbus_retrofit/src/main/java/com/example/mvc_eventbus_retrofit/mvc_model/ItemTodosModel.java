package com.example.mvc_eventbus_retrofit.mvc_model;

import com.example.mvc_eventbus_retrofit.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemTodosModel {

    private final List<ItemTodo> mItemTodoList = new ArrayList<>();

    private int mItemCount = 0;

    private boolean mIsBusy = false;

    public ItemTodosModel() {}

    public List<ItemTodo> getItemTodoList() {
        return mItemTodoList;
    }

    public int getItemCount() {
        return mItemCount;
    }

    public void load() {
        if (mIsBusy) {
            return;
        }

        mIsBusy = true;
        getTodos();

    }

    private void getTodos() {

        /*Create handle for the RetrofitInstance interface*/
        NetworkManager.ApiService service = NetworkManager.getRetrofitInstance().create(NetworkManager.ApiService.class);

        Call<List<ItemTodo>> call = service.getTodos();

        call.enqueue(new Callback<List<ItemTodo>>() {

            @Override
            public void onResponse(Call<List<ItemTodo>> call, Response<List<ItemTodo>> response) {

                List<ItemTodo> result = response.body();

                mItemTodoList.clear();
                mItemTodoList.addAll(result);
                mItemCount = result.size();
                mIsBusy = false;

                // EventBus
                EventBus.getDefault().post(new TodosLoadedEvent(true));
            }

            @Override
            public void onFailure(Call<List<ItemTodo>> call, Throwable t) { }

        });
    }

    public static class TodosLoadedEvent {

        private boolean isSuccess;

        private TodosLoadedEvent(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public boolean isSuccess() {
            return isSuccess;
        }

    }

}
