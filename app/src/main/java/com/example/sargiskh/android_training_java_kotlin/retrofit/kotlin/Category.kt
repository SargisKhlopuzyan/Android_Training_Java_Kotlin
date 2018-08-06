package com.example.sargiskh.android_training_java_kotlin.retrofit.kotlin

import com.google.gson.annotations.SerializedName

class Category (
        @SerializedName("albumId")
        val albumId : Int,

        @SerializedName("id")
        val  id : Int,

        @SerializedName("title")
        val title : String,

        @SerializedName("url")
        val url : String,

        @SerializedName("thumbnailUrl")
        val thumbnailUrl : String)