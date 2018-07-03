package com.example.sargiskh.android_training_java_kotlin.retrofit.kotlin

import com.google.gson.annotations.SerializedName

class Category (
        @SerializedName("albumId")
        val albumId : Integer,

        @SerializedName("id")
        val  id : Integer,

        @SerializedName("title")
        val title : String,

        @SerializedName("url")
        val url : String,

        @SerializedName("thumbnailUrl")
        val thumbnailUrl : String)