package com.example.sargiskh.android_training_java_kotlin.ok_http.kotlin

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.sargiskh.android_training_java_kotlin.R
import com.example.sargiskh.android_training_java_kotlin.ok_http.kotlin.network_client.NetworkClient
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity_Kotlin_Synchronous_Calls_OkHttp_GET : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kotlin_synchronous_calls_ok_http_get)

        val textView = findViewById<TextView>(R.id.txtString)

        GetJsonWithOkHttpClient(textView).execute()
    }

    open class GetJsonWithOkHttpClient(textView: TextView) : AsyncTask<Unit, Unit, String>() {

        val mInnerTextView = textView

        override fun doInBackground(vararg params: Unit?): String {
            val networkClient = NetworkClient()
            val stream = BufferedInputStream(networkClient.get("https://raw.githubusercontent.com/irontec/android-kotlin-samples/master/common-data/bilbao.json"))
            return readStream(stream)
        }

        private fun readStream(inputStream: BufferedInputStream): String {
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val streamBuilder = StringBuilder()
            bufferedReader.forEachLine { streamBuilder.append(it) }
            return streamBuilder.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            mInnerTextView.text = result
        }

    }
}
