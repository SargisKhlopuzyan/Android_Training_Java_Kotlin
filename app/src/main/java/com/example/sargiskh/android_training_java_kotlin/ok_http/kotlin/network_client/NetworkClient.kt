package com.example.sargiskh.android_training_java_kotlin.ok_http.kotlin.network_client

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.InputStream

class NetworkClient {

    fun get(url: String): InputStream {
        val request = Request.Builder().url(url).build()
        val response = OkHttpClient().newCall(request).execute()
        val body = response.body()
        // body.toString() returns a string representing the object and not the body itself, probably
        // kotlins fault when using third party libraries. Use byteStream() and convert it to a String
        return body!!.byteStream()
    }

}