package com.example.mvc_eventbus_retrofit.network;

import com.example.mvc_eventbus_retrofit.util.Const;
import com.example.mvc_eventbus_retrofit.mvc_model.ItemPhoto;
import com.example.mvc_eventbus_retrofit.mvc_model.ItemTodo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class NetworkManager {

    public interface ApiService {

        // For Test
        @GET(Const.API_TODOS)
        Call<List<ItemTodo>> getTodos();

        @GET(Const.API_PHOTOS)
        Call<List<ItemPhoto>> getPhotos();

    }

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Const.API_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }

    // Gson
    private static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
}
