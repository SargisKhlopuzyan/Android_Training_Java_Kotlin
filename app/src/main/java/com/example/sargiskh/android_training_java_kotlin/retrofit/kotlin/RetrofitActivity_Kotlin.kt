package com.example.sargiskh.android_training_java_kotlin.retrofit.kotlin

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.sargiskh.android_training_java_kotlin.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitActivity_Kotlin : AppCompatActivity() {

    private var adapter : Adapter_Kotlin ?= null
    private var recyclerView : RecyclerView ?= null
    private var progressDialog : ProgressDialog ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_kotlin)

        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Loading....")
        progressDialog!!.show()


        val apiService = ApiInterface.create()
        val call = apiService.getAllPhoto()

        call.enqueue(object : Callback<List<Category>> {

            override fun onResponse(call: Call<List<Category>>?, response: Response<List<Category>>?) {

                if (response != null) {
                    if (progressDialog != null && progressDialog!!.isShowing()) {
                        progressDialog!!.dismiss()
                    }
                    generateDataList(response.body())
                }
            }

            override fun onFailure(call: Call<List<Category>>?, t: Throwable?) {
                if (progressDialog != null && progressDialog!!.isShowing()) {
                    progressDialog!!.dismiss()
                }
                Toast.makeText(this@RetrofitActivity_Kotlin, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun generateDataList(photoList: List<Category>?) {
        recyclerView = findViewById(R.id.recyclerView)
        adapter = Adapter_Kotlin(this, photoList)
        val layoutManager = LinearLayoutManager(this@RetrofitActivity_Kotlin)
        recyclerView!!.setLayoutManager(layoutManager)
        recyclerView!!.setAdapter(adapter)
    }

}
