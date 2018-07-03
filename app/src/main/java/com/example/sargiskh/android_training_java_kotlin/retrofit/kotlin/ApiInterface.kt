package com.example.sargiskh.android_training_java_kotlin.retrofit.kotlin

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("/photos")
    abstract fun getAllPhoto(): Call<List<Category>>

    companion object Factory {
        val BASE_URL = "https://jsonplaceholder.typicode.com"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

}