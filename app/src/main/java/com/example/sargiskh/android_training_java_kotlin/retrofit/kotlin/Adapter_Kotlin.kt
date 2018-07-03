package com.example.sargiskh.android_training_java_kotlin.retrofit.kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sargiskh.android_training_java_kotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_row.view.*

class Adapter_Kotlin(val context : Context, val dataList : List<Category> ?= null) : RecyclerView.Adapter<Adapter_Kotlin.ViewHolder_Kotlin>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder_Kotlin {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.custom_row, parent, false)
        return ViewHolder_Kotlin(view)
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder_Kotlin, position: Int) {

        holder.textViewTitle.text = dataList!!.get(position).title

        val builder = Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context))
        builder.downloader(com.squareup.picasso.OkHttp3Downloader(context));
        builder.build()
                .load(dataList.get(position).thumbnailUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageViewCover)
    }

    class ViewHolder_Kotlin(view : View) : RecyclerView.ViewHolder(view) {
        val textViewTitle = view.title
        val imageViewCover = view.imageViewCover
    }

}

